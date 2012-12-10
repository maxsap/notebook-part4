package com.openshift.notebook.core.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.openshift.notebook.core.domain.Profile;
import com.openshift.notebook.core.repository.jpa.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Inject
	ProfileRepository profileRepository;
	
	@Override
	public Profile createProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	@Cacheable(value = "profiles", key = "#id")
	public Profile findProfile(Long id) {
		try {
			System.out.println("Sleeping for 5 minutes");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		return profileRepository.findOne(id);
	}

	@Override
	@CacheEvict(value="profiles",key="#updatedProfile.id")
	public Profile updateProfile(Profile updatedProfile) {
		return profileRepository.save(updatedProfile);
	}

	@Override
	@CacheEvict(value="profiles",key="#id")
	public void deleteProfile(Long id) {
		profileRepository.delete(id);
	}

	@Override
	public List<Profile> findAllProfiles() {
		return profileRepository.findAll();
	}

}
