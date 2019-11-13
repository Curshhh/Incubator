package code.dao;

import code.model.patientInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface patientDao {

    /**
     * 添加患者信息
     * @param patientinfo
     */
    @Insert("insert into patient(name,age,sex,birthday) value(#{name},#{age},#{sex},#{birthday})")
    public void addPatient(patientInfo patientinfo);

}
