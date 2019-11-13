package code.service;

import code.dao.SampleDao;
import code.model.SampleInfo;
import code.until.PicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SampleService {

    @Autowired
    private SampleDao sampleDao;

    /**
     * 根据患者id查询所有信息
     * @param sample_id
     * @return
     */
    public List<SampleInfo> getAllBySamlp_id(long sample_id) {

        return this.sampleDao.findAllBySample_id(sample_id);

    }

    /**
     * 样本id返回实体类
     * @param sample_id
     * @return
     */
    public SampleInfo getAllBySampleId(long sample_id){
        return sampleDao.getInfoBySampleId(sample_id);
    }
    /**
     * 插入信息
     * @param sampleInfo
     */
    public  void addInfo(SampleInfo sampleInfo){
       sampleDao.addInfo(sampleInfo);
    }

    /**
     * 修改第二时间图片
     * @param sample_id
     * @param photo
     * @return
     */
    public boolean setPhoto(long sample_id,String duration,String photo,String take_time,String status){
        try {
            sampleDao.updatephoto(sample_id,duration, photo,take_time,status);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * 获取初始时间
     * @param sample_id
     * @return
     */
    public String selectStartTime(long sample_id){
        String Start_time=sampleDao.selectStartTime(sample_id);
        return Start_time;
    }

    /**
     *根据相似度返回布尔值
     * @param image1
     * @param image2
     * @return
     */
    public boolean returnStatus(File image1,File image2){
        PicUtil picUtil=new PicUtil();
        try {
            double similarity=picUtil.getSimilarity(image1,image2);//相似度
            if((int)similarity < 70) { //相似度相差小于70%
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String queryStartPhotoUrl(long sample_id){
        String url=sampleDao.selectStartPhotoBySample_id(sample_id);
        return url;
    }
}
