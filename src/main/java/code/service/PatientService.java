package code.service;

import code.dao.patientDao;
import code.model.patientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private patientDao patientdao;

    /**
     * 添加患者信息
     * @param patientinfo
     * @return
     */
    public void addpatientInfo(patientInfo patientinfo){

           patientdao.addPatient(patientinfo);


    }


}
