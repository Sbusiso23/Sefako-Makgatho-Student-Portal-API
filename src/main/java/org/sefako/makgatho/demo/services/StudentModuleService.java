package org.sefako.makgatho.demo.services;

import java.util.Date;
import java.util.List;

import org.sefako.makgatho.demo.models.StudentModule;
import org.sefako.makgatho.demo.models.dto.StudentModuleDTO;
import org.sefako.makgatho.demo.repositories.ModuleRepository;
import org.sefako.makgatho.demo.repositories.StudentCourseRepository;
import org.sefako.makgatho.demo.repositories.StudentModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentModuleService {

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	StudentModuleRepository studentModuleRepository;
	
	@Autowired
	StudentCourseRepository studentCourseRepository;
	
	public List<StudentModule> all()
	{
		return studentModuleRepository.findAll();
	}
	
	public StudentModule find(Integer id)
	{
		return studentModuleRepository.findById(id).get();
	}
	
	public boolean save(StudentModuleDTO studentModuleDTO)
	{
		if(studentCourseRepository.existsById(studentModuleDTO.getStudent_course_id()) && moduleRepository.existsById(studentModuleDTO.getModule_id()))
		{
			StudentModule module = new StudentModule();
			module.setCourse(studentCourseRepository.findById(studentModuleDTO.getStudent_course_id()).get());
			module.setModule(moduleRepository.findById(studentModuleDTO.getModule_id()).get());
			module.setRegisteredAt(new Date());
			studentModuleRepository.save(module);
			return true;
		}else
			return false;
	}
}