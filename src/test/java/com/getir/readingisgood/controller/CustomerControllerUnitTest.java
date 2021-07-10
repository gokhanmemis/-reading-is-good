package com.getir.readingisgood.controller;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.model.User;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.util.GetirResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
class CustomerControllerUnitTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerController customerController;

    @Test
    public void createUserLogin() throws Exception {
        String uri = "/getir/api/customer/findAll";
        User user = new User();
        user.setUserName("3");
        user.setToken("Ginger");
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        String inputJson = "{\"username\":\"test\",\"password\":\"test\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(405, status);
        String content = mvcResult.getResponse().getContentAsString();
        //assertEquals(content, "Product is created successfully");
    }



    @Test
    void findAll() {
        assertEquals(customerController.findAll().getData().size(),4);
    }

    @Test
    void saveOrUpdateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test");
        customer.setFirstName("test");
        customer.setLastName("test");
        //customerRepository.save(customer);
        assertEquals(customer.getId(),null);
        //when(customerController.saveOrUpdateCustomer()).thenReturn(null);
    }


    @Test
    public void contextLoads() throws Exception {
        assertThat(customerController).isNotNull();
    }


    @Test
    public void getORderByCustomerIdSizeIntegrationTest(){
        GetirResponse<List<Order>> listORder = customerController.getOrderByCustomerId(1L,0,100);
        assertEquals(listORder.getData().size(),8);
    }

}