package com.employee.manager.paypal.EmployeeManagerPaypal;

import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeExists;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.AddressModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.PassportModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.serviceImpl.EmployeeServiceImpl;
import com.employee.manager.paypal.EmployeeManagerPaypal.VO.AddressVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.VO.EmployeeVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.VO.PassportVO;
import com.employee.manager.paypal.EmployeeManagerPaypal.exception.EmployeeNotFound;
import com.employee.manager.paypal.EmployeeManagerPaypal.model.EmployeeModel;
import com.employee.manager.paypal.EmployeeManagerPaypal.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeManagerPaypalApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@MockBean
	private EmployeeRepository employeeRepository;

	public void getEmpTest() {
		List<EmployeeModel> employeeModels = new ArrayList<>();
		PassportModel passportM1 = new PassportModel(1, "ab");
		AddressModel addressM1 = new AddressModel(1, "bhopalllllll");
		List<AddressModel> addressModelList1 = new ArrayList<>();
		addressModelList1.add(addressM1);
		PassportModel passportM2 = new PassportModel(2, "xy");
		AddressModel addressM2 = new AddressModel(2, "pune");
		List<AddressModel> addressModelList2 = new ArrayList<>();
		addressModelList2.add(addressM2);
		employeeModels.add(new EmployeeModel(1, "xyz", 20, addressModelList1, passportM1));
		employeeModels.add(new EmployeeModel(2, "abc", 30, addressModelList2, passportM2));

		when(employeeRepository.findAll()).thenReturn(employeeModels);
		assertEquals(2, employeeServiceImpl.findAllEmp().size());
		List<EmployeeVO> employeeVOS = employeeServiceImpl.findAllEmp();
		log.debug("model{}", employeeModels);
		log.debug("vo{}", employeeVOS);
		System.out.println("model" + employeeModels);
		System.out.println("vo" + employeeVOS);

		assertNotNull(employeeVOS);

		PassportVO passportVO = new PassportVO(1, "ab");
		PassportVO passportVO1 = new PassportVO(2, "xy");
		AddressVO addressVO = new AddressVO(1, "bhopalllllll");
		List<AddressVO> addressVOList = new ArrayList<>();
		addressVOList.add(addressVO);
		AddressVO addressVO1 = new AddressVO(2, "pune");
		List<AddressVO> addressVOList1 = new ArrayList<>();
		addressVOList1.add(addressVO1);
		employeeVOS.stream().forEach(emp -> {
			if(emp.getEmpId()==1)
			{
				assertEquals(emp.getAge(), 20);
				assertEquals(emp.getName(), "xyz");
				assertEquals(emp.getPassport(), passportVO);
				assertEquals(emp.getAddressList(), addressVOList);
			} else {
				assertEquals(emp.getAge(), 30);
				assertEquals(emp.getName(), "abc");
				assertEquals(emp.getPassport(), passportVO1);
				assertEquals(emp.getAddressList(), addressVOList1);
			}
		});
	}

	@Test
	public void getEmpByIdTestSuccess() throws Exception {
		int id = 1;
		PassportModel passportM1 = new PassportModel(1, "ab");
		AddressModel addressM1 = new AddressModel(1, "bhopalllllll");
		AddressModel addressM2=new AddressModel(2,"pune");
		List<AddressModel> addressModelList1 = new ArrayList<>();
		addressModelList1.add(addressM1);
		addressModelList1.add(addressM2);
		EmployeeModel employeeModel = new EmployeeModel(1, "xyz", 20, addressModelList1, passportM1);
		EmployeeVO expected = new DozerBeanMapper().map(employeeModel, EmployeeVO.class);
		when(employeeRepository.findById(id)).thenReturn(employeeModel);
		assertEquals(expected, employeeServiceImpl.findByID(id));
		List<EmployeeVO> employeeVOS = employeeServiceImpl.findAllEmp();
		assertNotNull(employeeVOS);
		PassportVO passportVo = new PassportVO(1, "ab");
		AddressVO addressVo1 = new AddressVO(1, "bhopalllllll");
		AddressVO addressVo2 = new AddressVO(2, "pune");
		List<AddressVO> addressVoList1 = new ArrayList<>();
		addressVoList1.add(addressVo1);
		addressVoList1.add(addressVo2);
		for (EmployeeVO emp : employeeVOS) {
			assertEquals(emp.getAge(), 20);
			assertEquals(emp.getName(), "xyz");
			System.out.println(emp.getAddressList().getClass().getName());
			assertEquals(emp.getAddressList(), addressVoList1);
			assertEquals(emp.getPassport(), passportVo);
		}
	}

	@Test()
	public void getEmpByIdTestException() throws Exception {
		int id = 1;
		when(employeeRepository.findById(id)).thenReturn(null);
		assertThrows(EmployeeNotFound.class, () -> {
			employeeServiceImpl.findByID(id);
		});
	}

	@Test
	public void saveEmployeeTest() throws EmployeeExists {
		PassportVO passportVO = new PassportVO(1, "ab");
		AddressVO addressVO = new AddressVO(1, "bhopalllllll");
		List<AddressVO> addressVOList = new ArrayList<>();
		addressVOList.add(addressVO);
		EmployeeVO employeeVO = new EmployeeVO(1, "xyz", 66, addressVOList, passportVO);
		EmployeeModel employeeModel = new DozerBeanMapper().map(employeeVO, EmployeeModel.class);
		log.debug("vo{}", employeeVO);
		System.out.println(employeeVO + "syso");
		when(employeeRepository.save(employeeModel)).thenReturn(employeeModel);
		assertEquals(1, employeeServiceImpl.createEmp(employeeVO));
		List<EmployeeVO> employeeVOS = employeeServiceImpl.findAllEmp();
		assertNotNull(employeeVOS);
		for (EmployeeVO emp : employeeVOS) {
			assertEquals(emp.getAge(), 66);
			assertEquals(emp.getName(), "xyz");
			assertEquals(emp.getAddressList(), addressVO);
			assertEquals(emp.getPassport(), passportVO);
		}
	}


	@Test
	public void getEmpByNameTest() throws Exception {
		String name = "prerna";
		EmployeeModel employeeModel = new EmployeeModel(1, "xyz", 20, null, null);
		EmployeeVO expected = new DozerBeanMapper().map(employeeModel, EmployeeVO.class);
		when(employeeRepository.findByEmpName(name)).thenReturn(employeeModel);
		assertEquals(expected, employeeServiceImpl.findByEmpName(name));
		List<EmployeeVO> employeeVOS = employeeServiceImpl.findAllEmp();
		assertNotNull(employeeVOS);
		for (EmployeeVO emp : employeeVOS) {
			assertEquals(emp.getAge(), 20);
			assertEquals(emp.getName(), "xyz");
			assertEquals(emp.getAddressList(), null);
			assertEquals(emp.getPassport(), null);
		}
		System.out.println("model" + employeeModel);
		System.out.println("vo" + employeeVOS);
	}

	@Test()
	public void getEmpByNameTestException() throws Exception {
		String name = "prerna";
		when(employeeRepository.findByEmpName(name)).thenReturn(null);
		assertThrows(EmployeeNotFound.class, () -> {
			employeeServiceImpl.findByEmpName(name);
		});
	}

	@Test
	public void deleteEmpTest() throws Exception {
		int id = 1;
		EmployeeModel employeeModel = new EmployeeModel(1, "xyz", 20, null, null);
		when(employeeRepository.findById(id)).thenReturn(employeeModel);
		employeeServiceImpl.deleteByEmpId(id);
		verify(employeeRepository, Mockito.times(1)).deleteById(id);
	}
}