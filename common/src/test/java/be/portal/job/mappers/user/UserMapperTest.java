package be.portal.job.mappers.user;

import be.portal.job.dtos.auth.requests.AbstractRegisterRequest;
import be.portal.job.dtos.auth.requests.JobAdvertiserRegisterRequest;
import be.portal.job.dtos.auth.requests.JobSeekerRegisterRequest;
import be.portal.job.dtos.user.requests.UserUpdateRequest;
import be.portal.job.dtos.user.responses.JobAdvertiserResponse;
import be.portal.job.dtos.user.responses.JobSeekerResponse;
import be.portal.job.dtos.user.responses.UserResponse;
import be.portal.job.entities.*;
import be.portal.job.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper mapper;

    private static final Role role = new Role("TESTO", "Test role");

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void toJobAdvertiser() {
        JobAdvertiserRegisterRequest request = (JobAdvertiserRegisterRequest)
                getRegisterRequest(JobAdvertiserRegisterRequest.class);

        Set<Role> roles = Set.of(role);

        JobAdvertiser result = mapper.toJobAdvertiser(request, roles);

        assertUserEntity(request, result);
    }

    @Test
    void toJobSeeker() {
        JobSeekerRegisterRequest request = (JobSeekerRegisterRequest)
                getRegisterRequest(JobSeekerRegisterRequest.class);
        request.setGender(Gender.M);
        request.setBirthDate(LocalDate.of(2000, 1, 1));

        Set<Role> roles = Set.of(role);

        JobSeeker result = mapper.toJobSeeker(request, roles);

        assertUserEntity(request, result);
        assertEquals(request.getGender(), result.getGender());
        assertEquals(request.getBirthDate(), result.getBirthDate());
    }

    @Test
    void toEntity() {
        JobAdvertiserRegisterRequest jobAdvertiserRequest = (JobAdvertiserRegisterRequest)
                getRegisterRequest(JobAdvertiserRegisterRequest.class);

        JobSeekerRegisterRequest jobSeekerRequest = (JobSeekerRegisterRequest)
                getRegisterRequest(JobSeekerRegisterRequest.class);
        jobSeekerRequest.setGender(Gender.M);
        jobSeekerRequest.setBirthDate(LocalDate.of(2000, 1, 1));

        Set<Role> roles = Set.of(role);

        JobAdvertiser jobAdvertiser = mapper.toJobAdvertiser(jobAdvertiserRequest, roles);
        JobSeeker jobSeeker = mapper.toJobSeeker(jobSeekerRequest, roles);

        assertEquals(jobAdvertiser, mapper.toEntity(jobAdvertiserRequest, roles));
        assertEquals(jobSeeker, mapper.toEntity(jobSeekerRequest, roles));
    }

    @Test
    void map() {
        Role role1 = new Role("TESTO1", "Test role 1");
        Role role2 = new Role("TESTO2", "Test role 2");

        Set<Role> roles = Set.of(role1, role2);

        Set<String> result = mapper.map(roles);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(role1.getName()));
        assertTrue(result.contains(role2.getName()));
    }

    @Test
    void updateEntityFromRequest() {
        UserUpdateRequest request = new UserUpdateRequest(
                "test firstname",
                "test lastname",
                "123456789",
                "contacttest@test.be",
                "test street",
                "test city",
                1234,
                "test country"
        );

        JobAdvertiser jobAdvertiser = new JobAdvertiser();

        mapper.updateEntityFromRequest(request, jobAdvertiser);

        assertEquals(request.getFirstname(), jobAdvertiser.getFirstname());
        assertEquals(request.getLastname(), jobAdvertiser.getLastname());
        assertEquals(request.getPhoneNumber(), jobAdvertiser.getPhoneNumber());
        assertEquals(request.getContactEmail(), jobAdvertiser.getContactEmail());
        assertEquals(request.getStreet(), jobAdvertiser.getAddress().getStreet());
        assertEquals(request.getCity(), jobAdvertiser.getAddress().getCity());
        assertEquals(request.getZip(), jobAdvertiser.getAddress().getZip());
        assertEquals(request.getCountry(), jobAdvertiser.getAddress().getCountry());
    }

    @Test
    void fromUser() {
        User user = getUser(JobAdvertiser.class);

        UserResponse result = mapper.fromUser(user);

        assertUserResponse(user, result);
    }

    @Test
    void fromJobAdvertiser() {
        JobAdvertiser jobAdvertiser = (JobAdvertiser) getUser(JobAdvertiser.class);

        JobAdvertiserResponse result = mapper.fromJobAdvertiser(jobAdvertiser);

        assertUserResponse(jobAdvertiser, result);
    }

    @Test
    void fromJobSeeker() {
        JobSeeker jobSeeker = (JobSeeker) getUser(JobSeeker.class);

        JobSeekerResponse result = mapper.fromJobSeeker(jobSeeker);

        assertUserResponse(jobSeeker, result);
    }

    /**
     * Get a given class to instantiate.
     * @param requestClass the class of the request to get
     * @return the request
     */
    private AbstractRegisterRequest getRegisterRequest(Class<? extends AbstractRegisterRequest> requestClass) {
        try {
            AbstractRegisterRequest request = requestClass.getDeclaredConstructor().newInstance();
            request.setEmail("test@test.be");
            request.setPassword("adohoiben");
            request.setFirstname("test firstname");
            request.setLastname("test lastname");
            request.setPhoneNumber("123456789");
            request.setContactEmail("contacttest@test.be");
            request.setStreet("test street");
            request.setCity("test city");
            request.setZip(1234);
            request.setCountry("test country");

            return request;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a given class to instantiate.
     * @param userClass the class of the user to get
     * @return the user
     */
    private User getUser(Class<? extends User> userClass) {
        try {
            User user = userClass.getDeclaredConstructor().newInstance();
            user.setEmail("test@test.be");
            user.setFirstname("test firstname");
            user.setLastname("test lastname");
            user.setPhoneNumber("123456789");
            user.setContactEmail("contacttest@test.be");
            user.setAddress(new Address("test street", "test city", 1234, "test country"));
            user.setRoles(Set.of(role));

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Assert that the user and request have the same data.
     * @param request the request to check
     * @param user the user to check
     */
    private void assertUserEntity(AbstractRegisterRequest request, User user) {
        assertNotNull(request);
        assertNotNull(user);
        assertEquals(request.getEmail(), user.getEmail());
        assertEquals(request.getFirstname(), user.getFirstname());
        assertEquals(request.getLastname(), user.getLastname());
        assertEquals(request.getPhoneNumber(), user.getPhoneNumber());
        assertEquals(request.getContactEmail(), user.getContactEmail());
        assertEquals(request.getStreet(), user.getAddress().getStreet());
        assertEquals(request.getCity(), user.getAddress().getCity());
        assertEquals(request.getZip(), user.getAddress().getZip());
        assertEquals(request.getCountry(), user.getAddress().getCountry());
        assertEquals(role, user.getRoles().iterator().next());
    }

    /**
     * Assert that the user and response have the same data.
     * @param user the user to check
     * @param response the response to check
     */
    private void assertUserResponse(User user, UserResponse response) {
        assertNotNull(user);
        assertNotNull(response);
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getFirstname(), response.getFirstname());
        assertEquals(user.getLastname(), response.getLastname());
        assertEquals(user.getPhoneNumber(), response.getPhoneNumber());
        assertEquals(user.getContactEmail(), response.getContactEmail());
        assertEquals(user.getAddress().getStreet(), response.getStreet());
        assertEquals(user.getAddress().getCity(), response.getCity());
        assertEquals(user.getAddress().getZip(), response.getZip());
        assertEquals(user.getAddress().getCountry(), response.getCountry());
        assertEquals(mapper.map(user.getRoles()), response.getRoles());
    }
}