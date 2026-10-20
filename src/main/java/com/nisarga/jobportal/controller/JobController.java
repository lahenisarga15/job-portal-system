package com.nisarga.jobportal.controller;

import com.nisarga.jobportal.entity.Job;
import com.nisarga.jobportal.entity.AppUser;
import com.nisarga.jobportal.repository.JobRepository;
import com.nisarga.jobportal.repository.AppUserRepository;
import com.nisarga.jobportal.repository.JobApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller @RequestMapping("/jobs")
public class JobController {
 private final JobRepository jobs; private final AppUserRepository users; private final JobApplicationRepository applications;
 public JobController(JobRepository jobs,AppUserRepository users,JobApplicationRepository applications){this.jobs=jobs;this.users=users;this.applications=applications;}
 @GetMapping public String list(@RequestParam(required=false) String keyword,@RequestParam(required=false) String type,Model model){
  var results=(keyword==null||keyword.isBlank())?jobs.findAll():jobs.findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrLocationContainingIgnoreCase(keyword,keyword,keyword);
  if(type!=null&&!type.isBlank()) results=results.stream().filter(j->type.equalsIgnoreCase(j.getJobType())).toList();
  model.addAttribute("jobs",results);model.addAttribute("keyword",keyword==null?"":keyword);model.addAttribute("type",type==null?"":type);return "jobs/list";
 }
 @GetMapping("/{id}") public String detail(@PathVariable Long id,Model model){var job=jobs.findById(id).orElse(null);if(job==null)return "redirect:/jobs";model.addAttribute("job",job);return "jobs/detail";}
 @PostMapping("/{id}/apply") public String apply(@PathVariable Long id,Authentication auth,RedirectAttributes flash){
  AppUser applicant=users.findByEmail(auth.getName()).orElseThrow();Job job=jobs.findById(id).orElse(null);
  if(job==null){flash.addFlashAttribute("error","That job could not be found.");return "redirect:/jobs";}
  if(applications.existsByApplicantAndJob(applicant,job)){flash.addFlashAttribute("error","You have already applied for this job.");return "redirect:/jobs/"+id;}
  var application=new com.nisarga.jobportal.entity.JobApplication();application.setApplicant(applicant);application.setJob(job);applications.save(application);
  flash.addFlashAttribute("success","Application submitted successfully!");return "redirect:/applicant/applications";
 }
}
