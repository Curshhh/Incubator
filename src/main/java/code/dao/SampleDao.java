package code.dao;

import code.model.SampleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SampleDao {
    /**
     * 根据sample_id查询所有
     * @return
     * @param sample_id
     */
    @Select("select * from sample where sample_id = #{sample_id}")
   public List<SampleInfo> findAllBySample_id(long sample_id);

    /**
     * 返回实体类对象
     * @param sample_id
     * @return
     */
    @Select("select * from sample where sample_id =#{sample_id} ")
    public SampleInfo getInfoBySampleId(long sample_id);

    /**
     * 插入数据
     * @param sampleInfo
     */
    @Insert("Insert into sample(sample_id,name,start_time,duration,type,status,photo,start_photo,take_time) value(#{sample_id},#{name},#{start_time},#{duration},#{type},#{status},#{photo},#{start_photo},#{take_time})")
 public void addInfo(SampleInfo sampleInfo);

    /**
     * 修改图片
     * @param sample_id
     * @param photo
     * @param take_time
     */
    @Update("update sample set status=#{status},photo=#{photo},duration=#{duration},take_time=#{take_time} where sample_id=#{sample_id}")
    public void updatephoto(long sample_id,String duration,String photo,String take_time,String status);

    /**
     * 查询开始时间
     * @param sample_id
     * @return
     */
    @Select("select start_time from sample where sample_id=#{sample_id}")
    public String selectStartTime(long sample_id);

    @Select("select Start_photo from sample where sample_id=#{sample_id}")
    public String selectStartPhotoBySample_id(long sample_id);
}
