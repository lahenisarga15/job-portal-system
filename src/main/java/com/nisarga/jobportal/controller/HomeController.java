package com.nisarga.jobportal.controller;
import com.nisarga.jobportal.repository.AppUserRepository;
import com.nisarga.jobportal.repository.JobRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller public class HomeController {
 private final JobRepository jobs; private final AppUserRepository users;
 public HomeController(JobRepository jobs,AppUserRepository users){this.jobs=jobs;this.users=users;}
 @GetMapping("/") public String home(Model model){model.addAttribute("jobs",jobs.findAll().stream().sorted((a,b)->b.getPostedAt().compareTo(a.getPostedAt())).limit(6).toList());model.addAttribute("jobCount",jobs.count());model.addAttribute("employerCount",users.findAll().stream().filter(u->"EMPLOYER".equals(u.getRole())).count());return "index";}
 @GetMapping("/dashboard") public String dashboard(Authentication auth){return users.findByEmail(auth.getName()).map(u->"EMPLOYER".equals(u.getRole())?"redirect:/employer/dashboard":"redirect:/applicant/dashboard").orElse("redirect:/login");}
 @GetMapping("/access-denied") public String denied(){return "access-denied";}
}
