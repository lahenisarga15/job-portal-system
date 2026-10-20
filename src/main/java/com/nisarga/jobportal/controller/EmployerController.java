package com.nisarga.jobportal.controller;
import com.nisarga.jobportal.entity.Job;import com.nisarga.jobportal.entity.AppUser;import com.nisarga.jobportal.repository.*;
import jakarta.validation.Valid;import org.springframework.security.core.Authentication;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/employer") public class EmployerController {
 private final JobRepository jobs;private final AppUserRepository users;private final JobApplicationRepository applications;
 public EmployerController(JobRepository jobs,AppUserRepository users,JobApplicationRepository applications){this.jobs=jobs;this.users=users;this.applications=applications;}
 @GetMapping("/dashboard") public String dashboard(Authentication auth,Model model){AppUser u=users.findByEmail(auth.getName()).orElseThrow();model.addAttribute("myJobs",jobs.findByEmployerOrderByPostedAtDesc(u));model.addAttribute("applications",applications.findByJobEmployerOrderByAppliedAtDesc(u));return "employer/dashboard";}
 @GetMapping("/jobs/new") public String newJob(Model model){model.addAttribute("job",new Job());return "employer/job-form";}
 @PostMapping("/jobs") public String create(@Valid @ModelAttribute("job") Job job,BindingResult result,Authentication auth){if(result.hasErrors())return "employer/job-form";AppUser employer=users.findByEmail(auth.getName()).orElseThrow();job.setEmployer(employer);job.setPostedAt(java.time.LocalDateTime.now());jobs.save(job);return "redirect:/employer/dashboard?posted";}
 @PostMapping("/applications/{id}/status") public String status(@PathVariable Long id,@RequestParam String status,Authentication auth,RedirectAttributes flash){
  var application=applications.findById(id).orElse(null);AppUser employer=users.findByEmail(auth.getName()).orElseThrow();
  if(application==null||application.getJob().getEmployer()==null||!application.getJob().getEmployer().getId().equals(employer.getId())){flash.addFlashAttribute("error","Application not found for your jobs.");return "redirect:/employer/dashboard";}
  if(java.util.List.of("PENDING","SHORTLISTED","REJECTED","HIRED").contains(status))application.setStatus(status);applications.save(application);flash.addFlashAttribute("success","Application status updated.");return "redirect:/employer/dashboard";
 }
}
