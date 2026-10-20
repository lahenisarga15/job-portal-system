package com.nisarga.jobportal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="applications", uniqueConstraints=@UniqueConstraint(columnNames={"applicant_id","job_id"}))
public class JobApplication {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="applicant_id", nullable=false) private AppUser applicant;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="job_id", nullable=false) private Job job;
    private String status="PENDING";
    private LocalDateTime appliedAt=LocalDateTime.now();
    public Long getId(){return id;} public AppUser getApplicant(){return applicant;} public void setApplicant(AppUser v){applicant=v;}
    public Job getJob(){return job;} public void setJob(Job v){job=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDateTime getAppliedAt(){return appliedAt;}
}
