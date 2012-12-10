package com.openshift.notebook.core.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.openshift.notebook.core.domain.Profile;
import com.openshift.notebook.core.domain.ProfileBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
@ActiveProfiles("dev")
public class ProfileServiceTest {

	@Inject
	private ProfileService profileService;

	@Test
	public void shouldFindById() {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@test.com").withPassword("password").build();
		profileService.createProfile(profile);

		long startTime = System.currentTimeMillis();
		Profile persistedProfile = profileService.findProfile(profile.getId());
		long endTime = System.currentTimeMillis();

		System.out.println("Time in seconds " + (endTime - startTime) / 1000
				+ " seconds");

		long startTimeAgain = System.currentTimeMillis();
		persistedProfile = profileService.findProfile(profile.getId());
		long endTimeAgain = System.currentTimeMillis();

		System.out.println("Time in seconds " + (endTimeAgain - startTimeAgain)
				/ 1000 + " seconds");
	}

	@Test
	public void shouldUpdateTheProfileObject() {
		Profile profile = ProfileBuilder.profile().withActive(true)
				.withEmail("test@test.com").withPassword("password").build();
		profileService.createProfile(profile);

		long startTime = System.currentTimeMillis();
		Profile persistedProfile = profileService.findProfile(profile.getId());
		long endTime = System.currentTimeMillis();

		System.out.println("Time in seconds " + (endTime - startTime) / 1000
				+ " seconds");

		long startTimeAgain = System.currentTimeMillis();
		persistedProfile = profileService.findProfile(profile.getId());
		long endTimeAgain = System.currentTimeMillis();
		System.out.println("Time in seconds " + (endTimeAgain - startTimeAgain)
				/ 1000 + " seconds");

		profile.setActive(false);
		profileService.updateProfile(profile);

		startTime = System.currentTimeMillis();
		persistedProfile = profileService.findProfile(profile.getId());
		endTime = System.currentTimeMillis();

		System.out.println("Time in seconds " + (endTime - startTime) / 1000
				+ " seconds");

		startTimeAgain = System.currentTimeMillis();
		persistedProfile = profileService.findProfile(profile.getId());
		endTimeAgain = System.currentTimeMillis();

		System.out.println("Time in seconds " + (endTimeAgain - startTimeAgain)
				/ 1000 + " seconds");

	}
}
