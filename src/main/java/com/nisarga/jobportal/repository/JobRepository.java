package com.nisarga.jobportal.repository;
import com.nisarga.jobportal.entity.Job;
import com.nisarga.jobportal.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobRepository extends JpaRepository<Job,Long> {
 List<Job> findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrLocationContainingIgnoreCase(String title,String company,String location);
 List<Job> findByEmployerOrderByPostedAtDesc(AppUser employer);
}
