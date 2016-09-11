package com.extra;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.extra.domain.Administrator;
import com.extra.domain.Beneficiary;
import com.extra.domain.Description;
import com.extra.domain.Project;
import com.extra.domain.Resource;
import com.extra.domain.Skill;
import com.extra.domain.Task;
import com.extra.domain.User;
import com.extra.domain.Volunteer;

import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Extra1ApplicationTests {
	
	ProjectService service = new ProjectService();
	
	private void printProjects(List<Project> projects) {
		for(Project p : projects) {
			System.out.println(p.getTitle() + " [" + p.getLocation() + "]");
			for(Description d : p.getDescriptions()) {
				System.out.println("\t" + d.getText());
			}
			System.out.println("\tBeneficiaries:");
			for(Beneficiary b : p.getBeneficiaries()) {
				System.out.println("\t\t" + b.getFirstName() + " " + b.getLastName() + " [" + b.getBenefitWeight() * 100 + "%]");
			}
			System.out.println("\tTasks:");
			for(Task t : p.getTasks()) {
				if(t.getVolunteers().size() > 0) {
					String volunteers = t.getVolunteers().stream().map(v->v.getFirstName()).collect(Collectors.joining(","));
					System.out.println("\t\t" + t.getTitle() + "[" + t.getTimeframe() + "]" + "\t <<Volunteers:" + volunteers + ">>");
				} else {
					System.out.println("\t\t" + t.getTitle() + "[" + t.getTimeframe() + "]");
				}
				for(Resource r:t.getRequiredResources()) {
					System.out.println("\t\t\tRequire " + r.getName());
				}
			}
		}
	}

	@Test
	public void a1_contextLoads() {
	}

	//
	// Create an administrator: 'admin'
	//
	@Test
	public void a2_createAdministrators() { 
		Administrator admin1 = new Administrator();
		admin1.setAccount("admin");
		admin1.setPassword("123456");
		
		service.beginTransaction();
		service.createUser(admin1);	
		service.commitTransaction();
	}
	
	//
	// Create a volunteer: 'volunteer' [Frank]
	// Create a volunteer: 'volunteer2' [Luis]
	// Create a volunteer: 'volunteer3' [Tom]
	// Create a volunteer: 'volunteer4' [Phillip]
	// Create a volunteer: 'volunteer5' [Robert]
	// Create a volunteer: 'volunteer6' [James]
	// Create a volunteer: 'volunteer7' [Edward]
	//
	@Test
	public void a3_createVolunteers() {
		Volunteer v1 = new Volunteer();
		v1.setFirstName("Frank");
		v1.setAccount("volunteer");
		v1.setPassword("111211");
		
		Volunteer v2 = new Volunteer();
		v2.setFirstName("Luis");
		v2.setAccount("volunteer2");
		v2.setPassword("000000");
		
		
		Volunteer v3 = new Volunteer();
		v3.setFirstName("Tom");
		v3.setAccount("volunteer3");
		v3.setPassword("888888");

		Volunteer v4 = new Volunteer();
		v4.setFirstName("Phillip");
		v4.setAccount("volunteer4");
		v4.setPassword("1100");
		
		Volunteer v5 = new Volunteer();
		v5.setFirstName("Robert");
		v5.setAccount("volunteer5");
		v5.setPassword("abcdef");
		
		Volunteer v6 = new Volunteer();
		v6.setFirstName("James");
		v6.setAccount("volunteer6");
		v6.setPassword("aaaaaaaa");
	
		Volunteer v7 = new Volunteer();
		v7.setFirstName("Edward");
		v7.setAccount("volunteer7");
		v7.setPassword("891283");
		
		
		service.beginTransaction();
		service.createUser(v1);		
		service.createUser(v2);	
		service.createUser(v3);	
		service.createUser(v4);		
		service.createUser(v5);	
		service.createUser(v6);	
		service.createUser(v7);
		service.commitTransaction();
	}
	
	//
	// Create a project: 'CRM v1.0'
	// Create a project: 'EMS v1.0'
	// Create a project: 'PuzzleGame v1.0'
	// by the administrator
	//
	@SuppressWarnings("deprecation")
	@Test
	public void a4_adminCreateProjects() {
		
		Project p = new Project();
		p.setTitle("CRM v1.0");
		p.setLocation("Chicago");
		p.setStartDate(new Date(2012-1900,1,1));
		p.setEndDate(new Date(2012-1900,3,21));
		
		Description d = new Description();
		d.setText("A Customer Relationship Management system Version 1.0");
		
		p.addDescription(d);
		
		Project p2 = new Project();
		p2.setTitle("EMS v1.0");
		p2.setLocation("Seattle");
		p2.setStartDate(new Date(2012-1900,1,9));
		p2.setEndDate(new Date(2012-1900,5,30));
		
		Description d2 = new Description();
		d2.setText("Employee Management System Version 1.0");
		
		p2.addDescription(d2);
		
		Project p3 = new Project();
		p3.setTitle("PuzzleGame v1.0");
		p3.setLocation("San Francisco");
		p3.setStartDate(new Date(2012-1900,1,9));
		p3.setEndDate(new Date(2012-1900,5,30));
		
		Description d3 = new Description();
		d3.setText("Puzzle Game Version 1.0");
		
		p3.addDescription(d3);
		
		User u = service.getUser("admin");
		
		service.beginTransaction();
		service.createProject(p, u);
		service.createProject(p2, u);
		service.createProject(p3, u);
		service.commitTransaction();
	}
	
	//
	// Create a project: 'ERP v1.0'
	// by the volunteer
	//
	@SuppressWarnings("deprecation")
	@Test(expected=NoResultException.class)
	public void a5_volunteerCreateProjects() {
		
		Project p = new Project();
		p.setTitle("ERP v1.0");
		p.setLocation("Chicago");
		p.setStartDate(new Date(2012-1900,1,1));
		p.setEndDate(new Date(2012-1900,3,21));
		
		User u = service.getUser("volunteer");	
		service.beginTransaction();		
		service.createProject(p, u);		
		service.commitTransaction();
		
		service.getProject("ERP v1.0");		
	}
	
	//
	// Add tasks to project: 'CRM v1.0'
	//
	@Test
	public void a6_addTasksToProjectCRM() {
		
		Project p = service.getProject("CRM v1.0");
		
		Task t1 = new Task();
		t1.setTitle("Create Requirement Documents");
		t1.setTimeframe(2);
		p.addTask(t1);
		
		Task t2 = new Task();
		t2.setTitle("Database Design");
		t2.setTimeframe(3);
		p.addTask(t2);
		
		Task t3 = new Task();
		t3.setTitle("Backend Development");
		t3.setTimeframe(4);
		p.addTask(t3);

		Task t4 = new Task();
		t4.setTitle("Front-end Development");
		t4.setTimeframe(4);
		p.addTask(t4);
		
		
		t1.addRequiredResource(new Skill("Word"));
		t1.addRequiredResource(new Skill("Excel"));
		
		t2.addRequiredResource(new Skill("SQL"));
		
		t3.addRequiredResource(new Skill("SQL"));
		t3.addRequiredResource(new Skill("C#"));
		t3.addRequiredResource(new Skill("ASP.NET"));			
		
		t4.addRequiredResource(new Skill("Javascript"));
		t4.addRequiredResource(new Skill("JQuery"));
		t4.addRequiredResource(new Skill("HTML"));	
		
		
		User admin = service.getUser("admin");
		Volunteer volunteer = (Volunteer)service.getUser("volunteer");
		Volunteer volunteer2 = (Volunteer)service.getUser("volunteer2");
		Volunteer volunteer3 = (Volunteer)service.getUser("volunteer3");
		
		t1.addVolunteer(volunteer);
		t2.addVolunteer(volunteer2);
		t3.addVolunteer(volunteer2);
		t4.addVolunteer(volunteer3);
		
		
		service.beginTransaction();		
		service.updateProject(p, admin);		
		service.commitTransaction();	
	}
	
	//
	// Add tasks to project: 'EMS v1.0'
	//
	@Test
	public void a6_addTasksToProjectEMS() {
		
		Project p = service.getProject("EMS v1.0");
		
		Task t1 = new Task();
		t1.setTitle("Create Requirement Documents");
		t1.setTimeframe(2);
		p.addTask(t1);
		
		Task t2 = new Task();
		t2.setTitle("Database Design");
		t2.setTimeframe(3);
		p.addTask(t2);
		
		Task t3 = new Task();
		t3.setTitle("Backend Development");
		t3.setTimeframe(4);
		p.addTask(t3);

		Task t4 = new Task();
		t4.setTitle("Front-end Development");
		t4.setTimeframe(4);
		p.addTask(t4);
		
		
		t1.addRequiredResource(new Skill("Word"));
		t1.addRequiredResource(new Skill("Excel"));
		
		t2.addRequiredResource(new Skill("SQL"));
		
		t3.addRequiredResource(new Skill("SQL"));
		t3.addRequiredResource(new Skill("Java"));
		t3.addRequiredResource(new Skill("Spring"));			
		t3.addRequiredResource(new Skill("Hibernate"));		
		
		t4.addRequiredResource(new Skill("Javascript"));
		t4.addRequiredResource(new Skill("Bootstrap"));
		t4.addRequiredResource(new Skill("HTML"));	
		
		
		User admin = service.getUser("admin");
		Volunteer volunteer = (Volunteer)service.getUser("volunteer3");
		Volunteer volunteer2 = (Volunteer)service.getUser("volunteer4");
		Volunteer volunteer3 = (Volunteer)service.getUser("volunteer5");
		
		t1.addVolunteer(volunteer);
		t2.addVolunteer(volunteer2);
		t2.addVolunteer(volunteer2);
		t4.addVolunteer(volunteer3);
		
		service.beginTransaction();		
		service.updateProject(p, admin);		
		service.commitTransaction();	
	}

	//
	// Add tasks to project: 'PuzzleGame v1.0'
	//
	@Test
	public void a6_addTasksToProjectPuzzleGame() {
		
		Project p = service.getProject("PuzzleGame v1.0");
		
		Task t1 = new Task();
		t1.setTitle("Game Rules Design");
		t1.setTimeframe(2);
		p.addTask(t1);
		
		Task t2 = new Task();
		t2.setTitle("Art Design");
		t2.setTimeframe(3);
		p.addTask(t2);
		
		Task t3 = new Task();
		t3.setTitle("Android App Development");
		t3.setTimeframe(4);
		p.addTask(t3);

		Task t4 = new Task();
		t4.setTitle("iOS App Development");
		t4.setTimeframe(4);
		p.addTask(t4);
		
		
		t1.addRequiredResource(new Skill("Word"));
		t1.addRequiredResource(new Skill("Excel"));
		t1.addRequiredResource(new Skill("Math"));
		t1.addRequiredResource(new Skill("Game"));
		
		t2.addRequiredResource(new Skill("Photoshop"));
		
		t3.addRequiredResource(new Skill("Java"));
		t3.addRequiredResource(new Skill("Android"));
		
		t4.addRequiredResource(new Skill("Objective-C"));
		t4.addRequiredResource(new Skill("Swift"));
		t4.addRequiredResource(new Skill("iOS"));	
		
		
		User admin = service.getUser("admin");
		Volunteer volunteer = (Volunteer)service.getUser("volunteer5");
		Volunteer volunteer2 = (Volunteer)service.getUser("volunteer6");
		Volunteer volunteer3 = (Volunteer)service.getUser("volunteer7");
		Volunteer volunteer4 = (Volunteer)service.getUser("volunteer2");
		
		t1.addVolunteer(volunteer);
		t1.addVolunteer(volunteer2);
		t2.addVolunteer(volunteer);
		t2.addVolunteer(volunteer2);
		t3.addVolunteer(volunteer3);
		t4.addVolunteer(volunteer3);
		t4.addVolunteer(volunteer4);
		t4.addVolunteer(volunteer);
		
		service.beginTransaction();		
		service.updateProject(p, admin);		
		service.commitTransaction();	
	}
	
	//
	// Add beneficiary to project: 'CRM v1.0'
	//
	@Test
	public void a7_addBeneficiaryToProject() {
		
		Project p = service.getProject("CRM v1.0");
		
		Beneficiary b1 = new Beneficiary();
		b1.setFirstName("John");
		b1.setLastName("Doe");
		b1.setBenefitWeight(0.3);
		p.addBeneficiary(b1);

		Beneficiary b2= new Beneficiary();
		b2.setFirstName("Mike");
		b2.setLastName("Moini");
		b2.setBenefitWeight(0.7);
		p.addBeneficiary(b2);
		
		
		User admin = service.getUser("admin");
		
		service.beginTransaction();		
		service.updateProject(p, admin);		
		service.commitTransaction();	
	}
	
	//
	// List projects
	//
	@Test
	public void a8_listProjects() {
		System.out.println("--------------------- All Projects ---------------------");
		List<Project> projects = service.getAllProjects();
		assertEquals(3, projects.size());
		printProjects(projects);
	}
	
	//
	// List projects that require C++
	//
	@Test
	public void a9_listProjectsRequireCPP() {
		System.out.println("--------------------- Projects require C# ---------------------");
		List<Project> projects = service.getProjectsRequireSkill("C#");
		assertEquals(1, projects.size());
		printProjects(projects);
	}
	
	//
	// List projects that have keyword 'Game' in name
	//
	@Test
	public void a9_listProjectsByKeyword() {
		System.out.println("--------------------- Projects that have keyword 'Game' in name ---------------------");
		List<Project> projects = service.getProjectsByKeyword("Game");
		assertEquals(1, projects.size());
		printProjects(projects);
	}

	//
	// List projects where a volunteer 'Frank' have offered services
	//
	@Test
	public void a9_listProjectsByVolunteer() {
		System.out.println("--------------------- Projects where a volunteer 'Frank' have offered services ---------------------");
		List<Project> projects = service.getProjectsByVolunteer("Frank");
		assertEquals(1, projects.size());
		printProjects(projects);
	}
}
