package jp.co.axa.apidemo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
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
public class ApiDemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPut() throws Exception {

		String target_base_url = "/api/v1/employees";

		mvc.perform(MockMvcRequestBuilders.get(target_base_url + "/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content()
						.string("{\"id\":1,\"name\":\"David Fu\",\"salary\":9000000,\"department\":\"Engineering\"}"));

		// Time to get a raise!
		mvc.perform(MockMvcRequestBuilders.put(target_base_url + "/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"David Fu\",\"salary\":10000000,\"department\":\"Engineering\"}"))
				.andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get(target_base_url).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(
						"[{\"id\":1,\"name\":\"David Fu\",\"salary\":10000000,\"department\":\"Engineering\"},{\"id\":2,\"name\":\"Arisa Ota\",\"salary\":8000000,\"department\":\"Engineering\"}]"));
	}

}
