package code.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SampleInfo {
    private long sample_id;
    private long people_id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String start_time;
    private String duration;
    private String type;
    private String status;
    private String start_photo;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String take_time;
    private String photo;

    public long getSample_id() {
        return sample_id;
    }

    public void setSample_id(long sample_id) {
        this.sample_id = sample_id;
    }

    public long getPeople_id() {
        return people_id;
    }

    public void setPeople_id(long people_id) {
        this.people_id = people_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_photo() {
        return start_photo;
    }

    public void setStart_photo(String start_photo) {
        this.start_photo = start_photo;
    }

    public String getTake_time() {
        return take_time;
    }

    public void setTake_time(String take_time) {
        this.take_time = take_time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "SampleInfo{" +
                "sample_id=" + sample_id +
                ", people_id=" + people_id +
                ", name='" + name + '\'' +
                ", start_time='" + start_time + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", start_photo='" + start_photo + '\'' +
                ", take_time='" + take_time + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
