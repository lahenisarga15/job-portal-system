package com.nisarga.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String title;
    @NotBlank private String company;
    @NotBlank private String location;
    @NotBlank private String jobType;
    @NotBlank @Column(length = 4000) private String description;
    @PositiveOrZero private Double salary = 0.0;
    private String skills;
    private LocalDateTime postedAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "employer_id")
    private AppUser employer;

    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getCompany(){return company;} public void setCompany(String v){company=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public String getJobType(){return jobType;} public void setJobType(String v){jobType=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public Double getSalary(){return salary;} public void setSalary(Double v){salary=v;}
    public String getSkills(){return skills;} public void setSkills(String v){skills=v;}
    public LocalDateTime getPostedAt(){return postedAt;} public void setPostedAt(LocalDateTime v){postedAt=v;}
    public AppUser getEmployer(){return employer;} public void setEmployer(AppUser v){employer=v;}
}
