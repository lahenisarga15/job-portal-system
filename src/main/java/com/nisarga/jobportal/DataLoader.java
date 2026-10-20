package com.nisarga.jobportal;

import com.nisarga.jobportal.entity.AppUser;import com.nisarga.jobportal.entity.Job;import com.nisarga.jobportal.repository.AppUserRepository;import com.nisarga.jobportal.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataLoader {
 @Bean CommandLineRunner seedData(AppUserRepository users,JobRepository jobs,PasswordEncoder encoder){return args->{
  AppUser employer=users.findByEmail("employer@demo.com").orElseGet(()->{AppUser u=new AppUser();u.setFullName("Demo Employer");u.setEmail("employer@demo.com");u.setPassword(encoder.encode("Employer@123"));u.setRole("EMPLOYER");u.setCompanyName("BrightPath Technologies");return users.save(u);});
  if(jobs.count()==0){String[][] data={
   {"Java Developer","BrightPath Technologies","Pune, Maharashtra","Full-time","Build and maintain Java applications, write clean code, and collaborate with the product team.","450000","Java, Spring Boot, SQL"},
   {"Software Test Engineer","QualityNest Solutions","Pune, Maharashtra","Full-time","Prepare test cases, execute manual tests, report defects, and support regression testing.","360000","Manual Testing, SQL, Postman"},
   {"Frontend Developer Intern","PixelWave Studio","Remote","Internship","Build responsive web pages and work with designers to improve user experience.","120000","HTML, CSS, JavaScript"},
   {"Junior SQL Developer","DataSpring Labs","Mumbai, Maharashtra","Full-time","Write SQL queries, support data validation, and help maintain reports.","300000","SQL, MySQL, Excel"},
   {"QA Automation Trainee","TestCraft India","Hybrid - Pune","Internship","Learn and implement UI automation tests using Selenium and Java.","180000","Java, Selenium, TestNG"},
   {"Business Analyst","NextStep Digital","Nagpur, Maharashtra","Full-time","Gather requirements, document workflows, and coordinate with development and testing teams.","420000","Communication, Excel, SQL"}};
   for(String[] d:data){Job j=new Job();j.setTitle(d[0]);j.setCompany(d[1]);j.setLocation(d[2]);j.setJobType(d[3]);j.setDescription(d[4]);j.setSalary(Double.valueOf(d[5]));j.setSkills(d[6]);j.setEmployer(employer);jobs.save(j);}
  }
  users.findByEmail("applicant@demo.com").orElseGet(()->{AppUser u=new AppUser();u.setFullName("Demo Applicant");u.setEmail("applicant@demo.com");u.setPassword(encoder.encode("Applicant@123"));u.setRole("APPLICANT");return users.save(u);});
 };}
}
