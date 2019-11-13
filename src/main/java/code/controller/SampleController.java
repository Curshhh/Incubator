package code.controller;


import code.model.SampleInfo;
import code.model.patientInfo;
import code.service.PatientService;
import code.service.SampleService;
import code.until.IDCardUtil;
import code.until.JSONUtil;
import code.until.PicUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/incubator")
public class SampleController {

    @Autowired
    private SampleService sampleService;
    @Autowired
    private PatientService patientService;

    /**
     * 根据样本id查询全部内容返回list集合（搜索
     * @param request
     * @param sample_id
     * @return
     */
    @PostMapping("getaInfoAll")
    public List<SampleInfo> getSampleInfo(HttpServletRequest request, long sample_id){
        sample_id= Long.parseLong(request.getParameter("sample_id"));
        return sampleService.getAllBySamlp_id(sample_id);

    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("addInfo")
    @ResponseBody
    public JSONObject addInfo(HttpServletRequest request ){

        String status=null;
        PicUtil picUtil=new PicUtil();
        patientInfo patientinfo=new patientInfo();
        JSONUtil jsonUtil = new JSONUtil();
        IDCardUtil idCardUtil=new IDCardUtil();

        long sample_id= Long.parseLong(request.getParameter("sample_id"));
        SampleInfo sampleinfo= sampleService.getAllBySampleId(sample_id);
        if (sampleinfo!=null){

            return jsonUtil.fail("添加失败，已存在样本id");
        }else {

           long sampleId= Long.parseLong(request.getParameter("sample_id"));//样本编号
           String type=request.getParameter("type");//样本类型
            String name=request.getParameter("name");//患者姓名
            String IdCard=request.getParameter("idcard");//患者身份证号
            String start_photo=request.getParameter("start_photo");//第一时间图片
            String photo=request.getParameter("photo");//第二时间图片

            String sex=idCardUtil.getSex(IdCard);//身份证号获取性别
            long age =idCardUtil.getAge(IdCard);//身份证号获取年龄
           String birthday=IDCardUtil.getBirthday(IdCard);//获取出生年月

            try {
                //相似度
                File startPhoto = new File(start_photo);
                File curPhoto = new File(photo);
                if (sampleService.returnStatus(startPhoto, curPhoto) == true) {
                    status = "true";
                } else {
                    status = "false";
                }
            }catch (Exception ex){
                jsonUtil.fail("无法获取到图片");
            }

            //存入患者信息
            patientinfo.setAge(age);
            patientinfo.setSex(sex);
            patientinfo.setBirthday(birthday);
            patientinfo.setName(name);
            patientService.addpatientInfo(patientinfo);

            if (start_photo==null) {

            return jsonUtil.fail("初始图片不能为空");
            }else {
                //存入样本信息
                SampleInfo sampleinfo1 = new SampleInfo();
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//获取当前日期
                String time = df.format(date);
                try {
                    String timeMath = "第" + 0 + "天";
                    sampleinfo1.setSample_id(sampleId);
                    sampleinfo1.setName(name);
                    sampleinfo1.setType(type);
                    sampleinfo1.setStart_time(time);
                    sampleinfo1.setStart_photo(start_photo);
                    sampleinfo1.setPhoto(photo);
                    sampleinfo1.setDuration(timeMath);
                    sampleinfo1.setStatus(status);
                    sampleService.addInfo(sampleinfo1);
                }catch (Exception e){
                    return jsonUtil.fail("添加失败");
                }

            }

            return jsonUtil.success("添加成功");
        }

    }


    /**
     * 修改第二时间图片
     * @param request
     * @return
     */
    @RequestMapping("updatephoto")
    @ResponseBody
    public JSONObject updatePhoto(HttpServletRequest request){
        String status=null;
        JSONUtil jsonUtil=new JSONUtil();
        Date date = new Date();
        Date d1 = null;
        Date d2 = null;

        long sample_id= Long.parseLong(request.getParameter("sample_id"));
        String photo=request.getParameter("photo");

        try {
            String starttime=sampleService.selectStartTime(sample_id);//查询初始时间

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//获取当前日期
            String time = df.format(date);//存入的时间
        try {
            d1=df.parse(time);//当前第二时间照片存入的时间
            d2=df.parse(starttime);//初始时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
            long diff = d1.getTime() - d2.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);//初始时间和第二次时间的差
            String duration="第"+diffDays+"天";

            //设置status
            String startPic_url = sampleService.queryStartPhotoUrl(sample_id);
            File startPhoto = new File(startPic_url);
            File curPhoto = new File(photo);
            boolean similarity = sampleService.returnStatus(startPhoto, curPhoto);
            if (similarity==true){
                status="true";
            }else {
                status="false";
            }

            sampleService.setPhoto(sample_id,duration,photo,time,status);//存入第二时间图片和时间
            return jsonUtil.success("修改成功");
        }catch (Exception ex){
            return jsonUtil.fail("修改失败");
        }
    }

}
