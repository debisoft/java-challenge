package jp.co.axa.apidemo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiDemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDelete() throws Exception {

		String target_base_url = "/api/v1/employees";

		mvc.perform(MockMvcRequestBuilders.get(target_base_url).accept(MediaType.APPLICATION_JSON).with(user("user")))
				.andExpect(status().isOk()).andExpect(content().string(
						"[{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"},{\"id\":2,\"name\":\"Arisa Ota\",\"salary\":8000000,\"department\":\"Engineering\"},{\"id\":3,\"name\":\"Uber Eater\",\"salary\":3000000,\"department\":\"Contractor\"}]"));

		// delete Contractor!
		mvc.perform(MockMvcRequestBuilders.delete(target_base_url + "/3").contentType(MediaType.APPLICATION_JSON)
				.with(user("user"))).andExpect(status().isOk());

		// Contractor gone!
		mvc.perform(MockMvcRequestBuilders.get(target_base_url).accept(MediaType.APPLICATION_JSON).with(user("user")))
				.andExpect(status().isOk()).andExpect(content().string(
						"[{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"},{\"id\":2,\"name\":\"Arisa Ota\",\"salary\":8000000,\"department\":\"Engineering\"}]"));

	}

	@Test
	public void testGetAll() throws Exception {

		String target_base_url = "/api/v1/employees";

		mvc.perform(MockMvcRequestBuilders.get(target_base_url).accept(MediaType.APPLICATION_JSON).with(user("user")))
				.andExpect(status().isOk()).andExpect(content().string(
						"[{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"},{\"id\":2,\"name\":\"Arisa Ota\",\"salary\":8000000,\"department\":\"Engineering\"}]"));
	}

	@Test
	public void testGetOne() throws Exception {

		String target_base_url = "/api/v1/employees";

		mvc.perform(MockMvcRequestBuilders.get(target_base_url + "/1").accept(MediaType.APPLICATION_JSON)
				.with(user("user"))).andExpect(status().isOk())
				.andExpect(content()
						.string("{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"}"));
	}

	@Test
	public void testPost() throws Exception {

		String target_base_url = "/api/v1/employees";

		mvc.perform(MockMvcRequestBuilders.get(target_base_url + "/1").accept(MediaType.APPLICATION_JSON)
				.with(user("user"))).andExpect(status().isOk())
				.andExpect(content()
						.string("{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"}"));

		// Time to get a raise!
		mvc.perform(MockMvcRequestBuilders.post(target_base_url).param("id", "1").param("name", "David Fu")
				.param("salary", "12000000").param("department", "Engineering").with(user("user")))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get(target_base_url).accept(MediaType.APPLICATION_JSON).with(user("user")))
				.andExpect(status().isOk()).andExpect(content().string(
						"[{\"id\":1,\"name\":\"David Fu\",\"salary\":12000000,\"department\":\"Engineering\"},{\"id\":2,\"name\":\"Arisa Ota\",\"salary\":8000000,\"department\":\"Engineering\"}]"));

		// Reset!
		mvc.perform(MockMvcRequestBuilders.post(target_base_url).param("id", "1").param("name", "David Fu")
				.param("salary", "9000000").param("department", "Engineering").with(user("user")))
				.andExpect(status().isOk());
	}

	@Test
	public void testPut() throws Exception {

		String target_base_url = "/api/v1/employees";

		mvc.perform(MockMvcRequestBuilders.get(target_base_url + "/1").accept(MediaType.APPLICATION_JSON)
				.with(user("user"))).andExpect(status().isOk())
				.andExpect(content()
						.string("{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"}"));

		// Time to get a raise!
		mvc.perform(MockMvcRequestBuilders.put(target_base_url + "/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"David Fu\",\"salary\":10000000,\"department\":\"Engineering\"}")
				.with(user("user"))).andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get(target_base_url).accept(MediaType.APPLICATION_JSON).with(user("user")))
				.andExpect(status().isOk()).andExpect(content().string(
						"[{\"id\":1,\"name\":\"David Fu\",\"salary\":10000000,\"department\":\"Engineering\"},{\"id\":2,\"name\":\"Arisa Ota\",\"salary\":8000000,\"department\":\"Engineering\"}]"));

		// Reset!
		mvc.perform(MockMvcRequestBuilders.put(target_base_url + "/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"}")
				.with(user("user"))).andExpect(status().isOk());
	}

}