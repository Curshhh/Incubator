package code.until;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
    public JSONObject success(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "success");
        jsonObject.put("status", "1");
        jsonObject.put("data", object);
        return jsonObject;
    }

    public JSONObject fail(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "fail");
        jsonObject.put("status", "0");
        jsonObject.put("data", object);
        return jsonObject;
    }

//    public JSONObject jump(Object object) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("msg", "未登录");
//        jsonObject.put("status", "2");
//        jsonObject.put("data", object);
//        return jsonObject;
//    }
}