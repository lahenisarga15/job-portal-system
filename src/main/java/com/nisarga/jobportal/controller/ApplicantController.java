package com.nisarga.jobportal.controller;
import com.nisarga.jobportal.repository.*;import org.springframework.security.core.Authentication;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestMapping;
@Controller @RequestMapping("/applicant") public class ApplicantController {
 private final AppUserRepository users;private final JobApplicationRepository applications;private final JobRepository jobs;
 public ApplicantController(AppUserRepository users,JobApplicationRepository applications,JobRepository jobs){this.users=users;this.applications=applications;this.jobs=jobs;}
 @GetMapping("/dashboard") public String dashboard(Authentication auth,Model model){var u=users.findByEmail(auth.getName()).orElseThrow();model.addAttribute("myApplications",applications.findByApplicantOrderByAppliedAtDesc(u));model.addAttribute("recentJobs",jobs.findAll().stream().limit(4).toList());return "applicant/dashboard";}
 @GetMapping("/applications") public String applications(Authentication auth,Model model){var u=users.findByEmail(auth.getName()).orElseThrow();model.addAttribute("applications",applications.findByApplicantOrderByAppliedAtDesc(u));return "applicant/applications";}
}
