package com.sample.app.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sample.app.model.Employee;

/**
 * In-memory employee repository initialized at application startup.
 *
 * <p>
 * This repository replaces a database entirely — no JPA, no SQL, no Hibernate.
 * All 100 employees are created once in the constructor and held in an
 * immutable list.
 *
 * <p>
 * <b>Hierarchy (5 levels):</b>
 * 
 * <pre>
 *   CEO (Aarav Sharma, id=1)
 *     ├── VP Engineering (Priya Mehta, id=2)
 *     │     ├── Director Engineering – Bengaluru (Arjun Sharma, id=5)   → Managers 8,9
 *     │     ├── Director Engineering – Pune (Rohan Kulkarni, id=6)       → Managers 10,11
 *     │     └── Director Engineering – Hyderabad (Siddharth Verma, id=7) → Managers 12,13
 *     ├── VP Finance (Rajesh Patel, id=3)
 *     │     ├── Director Finance (Neha Joshi, id=14)                     → Managers 17,18
 *     │     ├── Director Operations (Priya Nair, id=15)                  → Managers 19,20
 *     │     └── Director Legal (Karthik Menon, id=16)                    → Managers 21,22
 *     └── VP Sales (Ananya Rao, id=4)
 *           ├── Director Sales (Rahul Mehta, id=23)                      → Managers 26,27
 *           ├── Director HR (Ananya Krishnan, id=24)                     → Managers 28,29
 *           └── Director Support (Nikhil Desai, id=25)                   → Managers 30,31
 * </pre>
 */
@Repository
public class EmployeeRepository {

	private static final Logger log = LoggerFactory.getLogger(EmployeeRepository.class);

	/** The complete in-memory dataset. Immutable after construction. */
	private final List<Employee> employees;

	/**
	 * Initializes and populates the in-memory employee list. Logs a summary after
	 * loading.
	 */
	public EmployeeRepository() {
		this.employees = Collections.unmodifiableList(buildEmployees());
		log.info("EmployeeRepository initialized with {} employees across {} levels of hierarchy.", employees.size(),
				5);
	}

	/**
	 * Returns the complete immutable list of all employees.
	 *
	 * @return all employees
	 */
	public List<Employee> findAll() {
		return employees;
	}

	// ─────────────────────────────────────────────────────────────────────────
	// Private data-building helpers
	// ─────────────────────────────────────────────────────────────────────────

	private List<Employee> buildEmployees() {
		List<Employee> list = new ArrayList<>(100);

		// ══════════════════════════════════════════════════════════════════════
		// LEVEL 1 — C-Suite
		// ══════════════════════════════════════════════════════════════════════

		list.add(emp(1L, "EMP-0001", "Aarav", "Sharma", "aarav.sharma@acmecorp.com", "+91-9876543210",
				"Chief Executive Officer", "Executive", "Bengaluru", "Karnataka", "India", null, null, 450000.0,
				LocalDate.of(2010, 1, 15), true));

		// ══════════════════════════════════════════════════════════════════════
		// LEVEL 2 — Vice Presidents
		// ══════════════════════════════════════════════════════════════════════
		list.add(emp(2L, "EMP-0002", "Vivaan", "Patel", "vivaan.patel@acmecorp.com", "+91-23456-23456",
				"Vice President", "Engineering", "Hyderabad", "Telangana", "India", 1L, "Aarav Sharma", 280000.0,
				LocalDate.of(2011, 3, 1), true));

		list.add(emp(3L, "EMP-0003", "Aditya", "Reddy", "aditya.reddy@acmecorp.com", "+91-34567-34567",
				"Vice President", "Finance", "Mumbai", "Maharashtra", "India", 1L, "Aarav Sharma", 265000.0,
				LocalDate.of(2012, 6, 15), true));

		list.add(emp(4L, "EMP-0004", "Ananya", "Iyer", "ananya.iyer@acmecorp.com", "+91-45678-45678", "Vice President",
				"Sales", "Chennai", "Tamil Nadu", "India", 1L, "Aarav Sharma", 270000.0, LocalDate.of(2011, 9, 1),
				true));

		// ══════════════════════════════════════════════════════════════════════
		// LEVEL 3 — Directors (9 directors, 3 per VP)
		// ══════════════════════════════════════════════════════════════════════

		// — Under VP Engineering (Vivaan Patel, id=2) —
		list.add(emp(5L, "EMP-0005", "Arjun", "Sharma", "arjun.sharma@acmecorp.com", "+91-56789-56789", "Director",
				"Engineering", "Bengaluru", "Karnataka", "India", 2L, "Vivaan Patel", 195000.0,
				LocalDate.of(2013, 4, 10), true));

		list.add(emp(6L, "EMP-0006", "Rohan", "Kulkarni", "rohan.kulkarni@acmecorp.com", "+91-67890-67890", "Director",
				"Engineering", "Pune", "Maharashtra", "India", 2L, "Vivaan Patel", 190000.0, LocalDate.of(2014, 2, 28),
				true));

		list.add(emp(7L, "EMP-0007", "Siddharth", "Verma", "siddharth.verma@acmecorp.com", "+91-78901-78901",
				"Director", "Engineering", "Hyderabad", "Telangana", "India", 2L, "Vivaan Patel", 185000.0,
				LocalDate.of(2013, 11, 1), true));

		// — Under VP Finance (Aditya Reddy, id=3) —
		list.add(emp(14L, "EMP-0014", "Neha", "Joshi", "neha.joshi@acmecorp.com", "+91-89012-89012", "Director",
				"Finance", "Mumbai", "Maharashtra", "India", 3L, "Aditya Reddy", 185000.0, LocalDate.of(2014, 5, 12),
				true));

		list.add(emp(15L, "EMP-0015", "Priya", "Nair", "priya.nair@acmecorp.com", "+91-90123-90123", "Director",
				"Operations", "Chennai", "Tamil Nadu", "India", 3L, "Aditya Reddy", 178000.0, LocalDate.of(2015, 1, 20),
				true));

		list.add(emp(16L, "EMP-0016", "Karthik", "Menon", "karthik.menon@acmecorp.com", "+91-91234-91234", "Director",
				"Legal", "Kochi", "Kerala", "India", 3L, "Aditya Reddy", 182000.0, LocalDate.of(2014, 8, 5), true));

		// — Under VP Sales (Ananya Iyer, id=4) —
		list.add(emp(23L, "EMP-0023", "Rahul", "Mehta", "rahul.mehta@acmecorp.com", "+91-92345-92345", "Director",
				"Sales", "Ahmedabad", "Gujarat", "India", 4L, "Ananya Iyer", 188000.0, LocalDate.of(2013, 7, 22),
				true));

		list.add(emp(24L, "EMP-0024", "Ananya", "Krishnan", "ananya.krishnan@acmecorp.com", "+91-93456-93456",
				"Director", "HR", "Bengaluru", "Karnataka", "India", 4L, "Ananya Iyer", 175000.0,
				LocalDate.of(2015, 3, 15), true));

		list.add(emp(25L, "EMP-0025", "Nikhil", "Desai", "nikhil.desai@acmecorp.com", "+91-94567-94567", "Director",
				"Support", "Pune", "Maharashtra", "India", 4L, "Ananya Iyer", 180000.0, LocalDate.of(2014, 10, 8),
				true));

		// ══════════════════════════════════════════════════════════════════════
		// LEVEL 4 — Managers (18 managers, 2 per director)
		// ══════════════════════════════════════════════════════════════════════

		// — Under Director Arjun Sharma (id=5, Bengaluru) —
		list.add(emp(8L, "EMP-0008", "Vikram", "Rao", "vikram.rao@acmecorp.com", "+91-95678-95678", "Manager",
				"Engineering", "Bengaluru", "Karnataka", "India", 5L, "Arjun Sharma", 135000.0,
				LocalDate.of(2015, 6, 1), true));

		list.add(emp(9L, "EMP-0009", "Deepa", "Menon", "deepa.menon@acmecorp.com", "+91-96789-96789", "Manager",
				"Engineering", "Hyderabad", "Telangana", "India", 5L, "Arjun Sharma", 130000.0,
				LocalDate.of(2016, 2, 14), true));

		// — Under Director Rohan Kulkarni (id=6, Pune) —
		list.add(emp(10L, "EMP-0010", "Amit", "Joshi", "amit.joshi@acmecorp.com", "+91-97890-97890", "Manager",
				"Engineering", "Pune", "Maharashtra", "India", 6L, "Rohan Kulkarni", 128000.0,
				LocalDate.of(2016, 4, 20), true));

		list.add(emp(11L, "EMP-0011", "Sneha", "Kulkarni", "sneha.kulkarni@acmecorp.com", "+91-98901-98901", "Manager",
				"Engineering", "Nagpur", "Maharashtra", "India", 6L, "Rohan Kulkarni", 125000.0,
				LocalDate.of(2016, 8, 10), true));

		// — Under Director Siddharth Verma (id=7, Hyderabad) —
		list.add(emp(12L, "EMP-0012", "Kiran", "Verma", "kiran.verma@acmecorp.com", "+91-99012-99012", "Manager",
				"Engineering", "Hyderabad", "Telangana", "India", 7L, "Siddharth Verma", 132000.0,
				LocalDate.of(2015, 10, 5), true));

		list.add(emp(13L, "EMP-0013", "Harish", "Gupta", "harish.gupta@acmecorp.com", "+91-90123-01234", "Manager",
				"Engineering", "Noida", "Uttar Pradesh", "India", 7L, "Siddharth Verma", 127000.0,
				LocalDate.of(2017, 1, 9), true));

		// — Under Director Neha Joshi (id=14, Mumbai) —
		list.add(emp(17L, "EMP-0017", "Rakesh", "Gupta", "rakesh.gupta@acmecorp.com", "+91-91234-12345", "Manager",
				"Finance", "Mumbai", "Maharashtra", "India", 14L, "Neha Joshi", 122000.0, LocalDate.of(2016, 3, 7),
				true));

		list.add(emp(18L, "EMP-0018", "Swathi", "Rao", "swathi.rao@acmecorp.com", "+91-92345-23456", "Manager",
				"Finance", "Pune", "Maharashtra", "India", 14L, "Neha Joshi", 118000.0, LocalDate.of(2017, 5, 22),
				true));

		// — Under Director Priya Nair (id=15, Chennai) —
		list.add(emp(19L, "EMP-0019", "Suresh", "Kumar", "suresh.kumar@acmecorp.com", "+91-93456-34567", "Manager",
				"Operations", "Chennai", "Tamil Nadu", "India", 15L, "Priya Nair", 115000.0, LocalDate.of(2016, 11, 30),
				true));

		list.add(emp(20L, "EMP-0020", "Pooja", "Singh", "pooja.singh@acmecorp.com", "+91-94567-45678", "Manager",
				"Operations", "New Delhi", "Delhi", "India", 15L, "Priya Nair", 112000.0, LocalDate.of(2017, 7, 18),
				true));

		// — Under Director Karthik Menon (id=16, Kochi) —
		list.add(emp(21L, "EMP-0021", "Meera", "Nambiar", "meera.nambiar@acmecorp.com", "+91-95678-56789", "Manager",
				"Legal", "Kochi", "Kerala", "India", 16L, "Karthik Menon", 120000.0, LocalDate.of(2016, 9, 12), true));

		list.add(emp(22L, "EMP-0022", "Nitin", "Bhat", "nitin.bhat@acmecorp.com", "+91-96789-67890", "Manager", "Legal",
				"Mangaluru", "Karnataka", "India", 16L, "Karthik Menon", 118000.0, LocalDate.of(2017, 2, 28), true));

		// — Under Director Rahul Mehta (id=23, Ahmedabad) —
		list.add(emp(26L, "EMP-0026", "Tarun", "Shah", "tarun.shah@acmecorp.com", "+91-97890-78901", "Manager", "Sales",
				"Ahmedabad", "Gujarat", "India", 23L, "Rahul Mehta", 115000.0, LocalDate.of(2016, 6, 5), true));

		list.add(emp(27L, "EMP-0027", "Bhavna", "Patel", "bhavna.patel@acmecorp.com", "+91-98901-89012", "Manager",
				"Sales", "Surat", "Gujarat", "India", 23L, "Rahul Mehta", 112000.0, LocalDate.of(2017, 4, 14), true));

		// — Under Director Ananya Krishnan (id=24, Bengaluru) —
		list.add(emp(28L, "EMP-0028", "Kavya", "Reddy", "kavya.reddy@acmecorp.com", "+91-99012-90123", "Manager", "HR",
				"Bengaluru", "Karnataka", "India", 24L, "Ananya Krishnan", 108000.0, LocalDate.of(2017, 8, 1), true));

		list.add(emp(29L, "EMP-0029", "Ravi", "Iyer", "ravi.iyer@acmecorp.com", "+91-90123-91234", "Manager",
				"Marketing", "Hyderabad", "Telangana", "India", 24L, "Ananya Krishnan", 110000.0,
				LocalDate.of(2018, 1, 15), true));

		// — Under Director Nikhil Desai (id=25, Pune) —
		list.add(emp(30L, "EMP-0030", "Aishwarya", "Kulkarni", "aishwarya.kulkarni@acmecorp.com", "+91-91234-92345",
				"Manager", "Support", "Pune", "Maharashtra", "India", 25L, "Nikhil Desai", 112000.0,
				LocalDate.of(2017, 11, 20), true));

		list.add(emp(31L, "EMP-0031", "Prashant", "Jain", "prashant.jain@acmecorp.com", "+91-92345-93456", "Manager",
				"Security", "Jaipur", "Rajasthan", "India", 25L, "Nikhil Desai", 115000.0, LocalDate.of(2018, 3, 10),
				true));

		// — Under Manager Vikram Rao (id=8) — Engineering, Bengaluru, India —
		list.add(emp(32L, "EMP-0032", "Aditya", "Verma", "aditya.verma@acmecorp.com", "+91-90111-10001",
				"Software Engineer", "Engineering", "Bengaluru", "Karnataka", "India", 8L, "Vikram Rao", 78000.0,
				LocalDate.of(2019, 7, 1), true));

		list.add(emp(33L, "EMP-0033", "Sneha", "Joshi", "sneha.joshi@acmecorp.com", "+91-90111-10002",
				"Senior Engineer", "Engineering", "Bengaluru", "Karnataka", "India", 8L, "Vikram Rao", 95000.0,
				LocalDate.of(2018, 4, 15), true));

		list.add(emp(34L, "EMP-0034", "Karan", "Malhotra", "karan.malhotra@acmecorp.com", "+91-90111-10003",
				"Lead Engineer", "Engineering", "Bengaluru", "Karnataka", "India", 8L, "Vikram Rao", 108000.0,
				LocalDate.of(2017, 6, 20), true));

		list.add(emp(35L, "EMP-0035", "Ishaan", "Gupta", "ishaan.gupta@acmecorp.com", "+91-90111-10004",
				"Software Engineer", "Engineering", "Bengaluru", "Karnataka", "India", 8L, "Vikram Rao", 76000.0,
				LocalDate.of(2020, 3, 2), true));

		// — Under Manager Deepa Menon (id=9) — Engineering, Hyderabad, India —
		list.add(emp(36L, "EMP-0036", "Tanmay", "Kulkarni", "tanmay.kulkarni@acmecorp.com", "+91-90111-10005",
				"Software Engineer", "Engineering", "Hyderabad", "Telangana", "India", 9L, "Deepa Menon", 80000.0,
				LocalDate.of(2019, 9, 16), true));

		list.add(emp(37L, "EMP-0037", "Lakshmi", "Prasad", "lakshmi.prasad@acmecorp.com", "+91-90111-10006",
				"Senior Engineer", "Engineering", "Hyderabad", "Telangana", "India", 9L, "Deepa Menon", 97000.0,
				LocalDate.of(2018, 7, 30), true));

		list.add(emp(38L, "EMP-0038", "Nikhil", "Deshpande", "nikhil.deshpande@acmecorp.com", "+91-90111-10007",
				"Architect", "Engineering", "Hyderabad", "Telangana", "India", 9L, "Deepa Menon", 115000.0,
				LocalDate.of(2017, 2, 1), true));

		list.add(emp(39L, "EMP-0039", "Ritika", "Saxena", "ritika.saxena@acmecorp.com", "+91-90111-10008",
				"Software Engineer", "Engineering", "Hyderabad", "Telangana", "India", 9L, "Deepa Menon", 79000.0,
				LocalDate.of(2020, 1, 6), true));

		// — Under Manager Amit Joshi (id=10) — Engineering, Pune, India —
		list.add(emp(40L, "EMP-0040", "Ananya", "Kulkarni", "ananya.kulkarni@acmecorp.com", "+91-90111-10009",
				"Senior Engineer", "Engineering", "Pune", "Maharashtra", "India", 10L, "Amit Joshi", 98000.0,
				LocalDate.of(2018, 11, 12), true));

		list.add(emp(41L, "EMP-0041", "Rohit", "Patil", "rohit.patil@acmecorp.com", "+91-90111-10010", "Lead Engineer",
				"Engineering", "Pune", "Maharashtra", "India", 10L, "Amit Joshi", 110000.0, LocalDate.of(2017, 9, 5),
				true));

		list.add(emp(42L, "EMP-0042", "Pooja", "Desai", "pooja.desai@acmecorp.com", "+91-90111-10011",
				"Software Engineer", "Engineering", "Pune", "Maharashtra", "India", 10L, "Amit Joshi", 82000.0,
				LocalDate.of(2019, 5, 27), true));

		list.add(emp(43L, "EMP-0043", "Sandeep", "Kulkarni", "sandeep.kulkarni@acmecorp.com", "+91-90111-10012",
				"Architect", "Engineering", "Pune", "Maharashtra", "India", 10L, "Amit Joshi", 118000.0,
				LocalDate.of(2016, 12, 19), true));

		// — Under Manager Sneha Kulkarni (id=11) — Engineering, Nagpur, India —
		list.add(emp(44L, "EMP-0044", "Chaitra", "Patil", "chaitra.patil@acmecorp.com", "+91-90111-10013",
				"Senior Engineer", "Engineering", "Nagpur", "Maharashtra", "India", 11L, "Sneha Kulkarni", 96000.0,
				LocalDate.of(2018, 8, 3), true));

		list.add(emp(45L, "EMP-0045", "Harsh", "Mehta", "harsh.mehta@acmecorp.com", "+91-90111-10014",
				"Software Engineer", "Engineering", "Nagpur", "Maharashtra", "India", 11L, "Sneha Kulkarni", 82000.0,
				LocalDate.of(2019, 4, 22), true));

		list.add(emp(46L, "EMP-0046", "Megha", "Sharma", "megha.sharma@acmecorp.com", "+91-90111-10015",
				"Lead Engineer", "Engineering", "Nagpur", "Maharashtra", "India", 11L, "Sneha Kulkarni", 109000.0,
				LocalDate.of(2017, 11, 14), true));

		list.add(emp(47L, "EMP-0047", "Nitin", "Rao", "nitin.rao@acmecorp.com", "+91-90111-10016", "Software Engineer",
				"Engineering", "Nagpur", "Maharashtra", "India", 11L, "Sneha Kulkarni", 80000.0,
				LocalDate.of(2020, 6, 1), false));

		// — Under Manager Kiran Verma (id=12) — Engineering, Hyderabad, India —
		list.add(emp(48L, "EMP-0048", "Harini", "Reddy", "harini.reddy@acmecorp.com", "+91-90111-10017",
				"Senior Engineer", "Engineering", "Hyderabad", "Telangana", "India", 12L, "Kiran Verma", 94000.0,
				LocalDate.of(2018, 10, 8), true));

		list.add(emp(49L, "EMP-0049", "Rakesh", "Naidu", "rakesh.naidu@acmecorp.com", "+91-90111-10018",
				"Software Engineer", "Engineering", "Hyderabad", "Telangana", "India", 12L, "Kiran Verma", 81000.0,
				LocalDate.of(2019, 12, 2), true));

		list.add(emp(50L, "EMP-0050", "Sowmya", "Iyer", "sowmya.iyer@acmecorp.com", "+91-90111-10019", "Architect",
				"Engineering", "Hyderabad", "Telangana", "India", 12L, "Kiran Verma", 116000.0,
				LocalDate.of(2017, 3, 25), true));

		// — Under Manager Harish Gupta (id=13) — Engineering, Noida, India —
		list.add(emp(51L, "EMP-0051", "Daivik", "Sharma", "daivik.sharma@acmecorp.com", "+91-90111-10020",
				"Software Engineer", "Engineering", "Noida", "Uttar Pradesh", "India", 13L, "Harish Gupta", 79000.0,
				LocalDate.of(2020, 4, 13), true));

		list.add(emp(52L, "EMP-0052", "Aakash", "Agarwal", "aakash.agarwal@acmecorp.com", "+91-90111-10021",
				"Senior Engineer", "Engineering", "Noida", "Uttar Pradesh", "India", 13L, "Harish Gupta", 92000.0,
				LocalDate.of(2019, 2, 18), true));

		list.add(emp(53L, "EMP-0053", "Rohit", "Bansal", "rohit.bansal@acmecorp.com", "+91-90111-10022",
				"Lead Engineer", "Engineering", "Noida", "Uttar Pradesh", "India", 13L, "Harish Gupta", 107000.0,
				LocalDate.of(2018, 6, 25), true));

		list.add(emp(54L, "EMP-0054", "Pallavi", "Mishra", "pallavi.mishra@acmecorp.com", "+91-90111-10023",
				"Software Engineer", "Engineering", "Noida", "Uttar Pradesh", "India", 13L, "Harish Gupta", 85000.0,
				LocalDate.of(2019, 10, 7), true));

		list.add(emp(55L, "EMP-0055", "Vivek", "Srivastava", "vivek.srivastava@acmecorp.com", "+91-90111-10024",
				"Software Engineer", "Engineering", "Noida", "Uttar Pradesh", "India", 13L, "Harish Gupta", 83000.0,
				LocalDate.of(2020, 8, 3), false));

		// — Under Manager Rakesh Gupta (id=17) — Finance, Mumbai, India —
		list.add(emp(56L, "EMP-0056", "Kavita", "Shah", "kavita.shah@acmecorp.com", "+91-90111-10025",
				"Finance Analyst", "Finance", "Mumbai", "Maharashtra", "India", 17L, "Rakesh Gupta", 72000.0,
				LocalDate.of(2019, 3, 11), true));

		list.add(emp(57L, "EMP-0057", "Rahul", "Kulkarni", "rahul.kulkarni@acmecorp.com", "+91-90111-10026",
				"Finance Analyst", "Finance", "Mumbai", "Maharashtra", "India", 17L, "Rakesh Gupta", 75000.0,
				LocalDate.of(2018, 9, 24), true));

		list.add(emp(58L, "EMP-0058", "Nisha", "Patel", "nisha.patel@acmecorp.com", "+91-90111-10027",
				"Finance Analyst", "Finance", "Mumbai", "Maharashtra", "India", 17L, "Rakesh Gupta", 73000.0,
				LocalDate.of(2020, 2, 17), true));

		// — Under Manager Swathi Rao (id=18) — Finance, Pune, India —
		list.add(emp(59L, "EMP-0059", "Manoj", "Patil", "manoj.patil@acmecorp.com", "+91-90111-10028",
				"Finance Analyst", "Finance", "Pune", "Maharashtra", "India", 18L, "Swathi Rao", 71000.0,
				LocalDate.of(2019, 6, 3), true));

		list.add(emp(60L, "EMP-0060", "Aarti", "Kulkarni", "aarti.kulkarni@acmecorp.com", "+91-90111-10029",
				"Finance Analyst", "Finance", "Pune", "Maharashtra", "India", 18L, "Swathi Rao", 74000.0,
				LocalDate.of(2018, 12, 10), true));

		list.add(emp(61L, "EMP-0061", "Naveen", "Joshi", "naveen.joshi@acmecorp.com", "+91-90111-10030",
				"Finance Analyst", "Finance", "Pune", "Maharashtra", "India", 18L, "Swathi Rao", 70000.0,
				LocalDate.of(2020, 5, 19), false));

		// — Under Manager Suresh Kumar (id=19) — Operations, Chennai, India —
		list.add(emp(62L, "EMP-0062", "Meera", "Pillai", "meera.pillai@acmecorp.com", "+91-90111-10031",
				"Support Engineer", "Operations", "Chennai", "Tamil Nadu", "India", 19L, "Suresh Kumar", 65000.0,
				LocalDate.of(2019, 8, 5), true));

		list.add(emp(63L, "EMP-0063", "Sanjay", "Nair", "sanjay.nair@acmecorp.com", "+91-90111-10032",
				"Support Engineer", "Operations", "Chennai", "Tamil Nadu", "India", 19L, "Suresh Kumar", 67000.0,
				LocalDate.of(2018, 11, 19), true));

		list.add(emp(64L, "EMP-0064", "Divya", "Krishnamurthy", "divya.krishnamurthy@acmecorp.com", "+91-90111-10033",
				"Support Engineer", "Operations", "Chennai", "Tamil Nadu", "India", 19L, "Suresh Kumar", 66000.0,
				LocalDate.of(2020, 1, 27), true));

		// — Under Manager Pooja Singh (id=20) — Operations, New Delhi, India —
		list.add(emp(65L, "EMP-0065", "Amit", "Tiwari", "amit.tiwari@acmecorp.com", "+91-90111-10034",
				"Support Engineer", "Operations", "New Delhi", "Delhi", "India", 20L, "Pooja Singh", 64000.0,
				LocalDate.of(2019, 11, 4), true));

		list.add(emp(66L, "EMP-0066", "Neha", "Sharma", "neha.sharma@acmecorp.com", "+91-90111-10035",
				"Support Engineer", "Operations", "New Delhi", "Delhi", "India", 20L, "Pooja Singh", 65000.0,
				LocalDate.of(2018, 5, 8), true));

		list.add(emp(67L, "EMP-0067", "Rohit", "Chauhan", "rohit.chauhan@acmecorp.com", "+91-90111-10036",
				"Support Engineer", "Operations", "New Delhi", "Delhi", "India", 20L, "Pooja Singh", 63000.0,
				LocalDate.of(2020, 9, 14), false));

		// — Under Manager Meera Nambiar (id=21) — Legal, Kochi, India —
		list.add(emp(68L, "EMP-0068", "Anand", "Menon", "anand.menon@acmecorp.com", "+91-90111-10037", "Legal Analyst",
				"Legal", "Kochi", "Kerala", "India", 21L, "Meera Nambiar", 78000.0, LocalDate.of(2019, 4, 16), true));

		list.add(emp(69L, "EMP-0069", "Keerthi", "Nair", "keerthi.nair@acmecorp.com", "+91-90111-10038",
				"Legal Analyst", "Legal", "Kochi", "Kerala", "India", 21L, "Meera Nambiar", 76000.0,
				LocalDate.of(2018, 10, 3), true));

		list.add(emp(70L, "EMP-0070", "Arvind", "Bhat", "arvind.bhat@acmecorp.com", "+91-90111-10039", "Legal Analyst",
				"Legal", "Kochi", "Kerala", "India", 21L, "Meera Nambiar", 79000.0, LocalDate.of(2020, 7, 20), true));

		// — Under Manager Nitin Bhat (id=22) — Legal, Mangaluru, India —
		list.add(emp(71L, "EMP-0071", "Anjali", "Shetty", "anjali.shetty@acmecorp.com", "+91-90111-10040",
				"Legal Analyst", "Legal", "Mangaluru", "Karnataka", "India", 22L, "Nitin Bhat", 77000.0,
				LocalDate.of(2019, 1, 7), true));

		list.add(emp(72L, "EMP-0072", "Karthik", "Hegde", "karthik.hegde@acmecorp.com", "+91-90111-10041",
				"Legal Analyst", "Legal", "Mangaluru", "Karnataka", "India", 22L, "Nitin Bhat", 75000.0,
				LocalDate.of(2018, 6, 14), true));

		list.add(emp(73L, "EMP-0073", "Pavithra", "Rao", "pavithra.rao@acmecorp.com", "+91-90111-10042",
				"Legal Analyst", "Legal", "Mangaluru", "Karnataka", "India", 22L, "Nitin Bhat", 76000.0,
				LocalDate.of(2020, 3, 25), false));

		// — Under Manager Tarun Shah (id=26) — Sales, Ahmedabad, India —
		list.add(emp(74L, "EMP-0074", "Ritesh", "Patel", "ritesh.patel@acmecorp.com", "+91-90111-10043",
				"Sales Executive", "Sales", "Ahmedabad", "Gujarat", "India", 26L, "Tarun Shah", 68000.0,
				LocalDate.of(2019, 9, 9), true));

		list.add(emp(75L, "EMP-0075", "Neha", "Desai", "neha.desai@acmecorp.com", "+91-90111-10044", "Sales Executive",
				"Sales", "Ahmedabad", "Gujarat", "India", 26L, "Tarun Shah", 70000.0, LocalDate.of(2018, 4, 2), true));

		list.add(emp(76L, "EMP-0076", "Jay", "Mehta", "jay.mehta@acmecorp.com", "+91-90111-10045", "Sales Executive",
				"Sales", "Ahmedabad", "Gujarat", "India", 26L, "Tarun Shah", 67000.0, LocalDate.of(2020, 11, 10),
				true));

		// — Under Manager Bhavna Patel (id=27) — Sales, Surat, India —
		list.add(emp(77L, "EMP-0077", "Dhruv", "Shah", "dhruv.shah@acmecorp.com", "+91-90111-10046", "Sales Executive",
				"Sales", "Surat", "Gujarat", "India", 27L, "Bhavna Patel", 69000.0, LocalDate.of(2019, 7, 21), true));

		list.add(emp(78L, "EMP-0078", "Riya", "Joshi", "riya.joshi@acmecorp.com", "+91-90111-10047", "Sales Executive",
				"Sales", "Surat", "Gujarat", "India", 27L, "Bhavna Patel", 71000.0, LocalDate.of(2018, 2, 6), true));

		list.add(emp(79L, "EMP-0079", "Yash", "Trivedi", "yash.trivedi@acmecorp.com", "+91-90111-10048",
				"Sales Executive", "Sales", "Surat", "Gujarat", "India", 27L, "Bhavna Patel", 68000.0,
				LocalDate.of(2020, 10, 12), false));

		// — Under Manager Kavya Reddy (id=28) — HR, Bengaluru, India —
		list.add(emp(80L, "EMP-0080", "Shruti", "Bhat", "shruti.bhat@acmecorp.com", "+91-90111-10049", "HR Executive",
				"HR", "Bengaluru", "Karnataka", "India", 28L, "Kavya Reddy", 62000.0, LocalDate.of(2019, 5, 30), true));

		list.add(emp(81L, "EMP-0081", "Tejas", "Kulkarni", "tejas.kulkarni@acmecorp.com", "+91-90111-10050",
				"HR Executive", "HR", "Bengaluru", "Karnataka", "India", 28L, "Kavya Reddy", 63000.0,
				LocalDate.of(2018, 9, 17), true));

		list.add(emp(82L, "EMP-0082", "Pallavi", "Gaikwad", "pallavi.gaikwad@acmecorp.com", "+91-90111-10051",
				"HR Executive", "HR", "Bengaluru", "Karnataka", "India", 28L, "Kavya Reddy", 61000.0,
				LocalDate.of(2020, 4, 6), true));

		// — Under Manager Ravi Iyer (id=29) — Marketing, Hyderabad, India —
		list.add(emp(83L, "EMP-0083", "Chitra", "Narayanan", "chitra.narayanan@acmecorp.com", "+91-90111-10052",
				"Marketing Specialist", "Marketing", "Hyderabad", "Telangana", "India", 29L, "Ravi Iyer", 65000.0,
				LocalDate.of(2019, 3, 24), true));

		list.add(emp(84L, "EMP-0084", "Vishal", "Kapoor", "vishal.kapoor@acmecorp.com", "+91-90111-10053",
				"Marketing Specialist", "Marketing", "Hyderabad", "Telangana", "India", 29L, "Ravi Iyer", 66000.0,
				LocalDate.of(2018, 8, 11), true));

		list.add(emp(85L, "EMP-0085", "Priyanka", "Rao", "priyanka.rao@acmecorp.com", "+91-90111-10054",
				"Marketing Specialist", "Marketing", "Hyderabad", "Telangana", "India", 29L, "Ravi Iyer", 64000.0,
				LocalDate.of(2020, 6, 8), true));

		// — Under Manager Aishwarya Kulkarni (id=30) — Support, Pune, India —
		list.add(emp(86L, "EMP-0086", "Rahul", "Jadhav", "rahul.jadhav@acmecorp.com", "+91-90111-10055",
				"Support Engineer", "Support", "Pune", "Maharashtra", "India", 30L, "Aishwarya Kulkarni", 67000.0,
				LocalDate.of(2019, 2, 14), true));

		list.add(emp(87L, "EMP-0087", "Snehal", "Patil", "snehal.patil@acmecorp.com", "+91-90111-10056",
				"Support Engineer", "Support", "Pune", "Maharashtra", "India", 30L, "Aishwarya Kulkarni", 68000.0,
				LocalDate.of(2018, 7, 28), true));

		list.add(emp(88L, "EMP-0088", "Mahesh", "Pawar", "mahesh.pawar@acmecorp.com", "+91-90111-10057",
				"Support Engineer", "Support", "Pune", "Maharashtra", "India", 30L, "Aishwarya Kulkarni", 65000.0,
				LocalDate.of(2020, 1, 20), true));

		list.add(emp(89L, "EMP-0089", "Aniket", "Shinde", "aniket.shinde@acmecorp.com", "+91-90111-10058",
				"Support Engineer", "Support", "Pune", "Maharashtra", "India", 30L, "Aishwarya Kulkarni", 64000.0,
				LocalDate.of(2021, 3, 1), true));

		// — Under Manager Prashant Jain (id=31) — Security, Jaipur, India —
		list.add(emp(90L, "EMP-0090", "Gaurav", "Mathur", "gaurav.mathur@acmecorp.com", "+91-90111-10059",
				"Security Analyst", "Security", "Jaipur", "Rajasthan", "India", 31L, "Prashant Jain", 88000.0,
				LocalDate.of(2019, 6, 10), true));

		list.add(emp(91L, "EMP-0091", "Nidhi", "Sharma", "nidhi.sharma@acmecorp.com", "+91-90111-10060",
				"Security Analyst", "Security", "Jaipur", "Rajasthan", "India", 31L, "Prashant Jain", 87000.0,
				LocalDate.of(2018, 11, 5), true));

		list.add(emp(92L, "EMP-0092", "Saurabh", "Agarwal", "saurabh.agarwal@acmecorp.com", "+91-90111-10061",
				"Security Analyst", "Security", "Jaipur", "Rajasthan", "India", 31L, "Prashant Jain", 86000.0,
				LocalDate.of(2020, 8, 17), false));

		// ── Additional employees to reach 100 ─────────────────────────────────

		// More Engineers under Vikram Rao (id=8)
		list.add(emp(93L, "EMP-0093", "Rahul", "Mehta", "rahul.mehta@acmecorp.com", "+91-90111-10062",
				"Software Engineer", "Engineering", "Bengaluru", "Karnataka", "India", 8L, "Vikram Rao", 77000.0,
				LocalDate.of(2021, 2, 15), true));

		list.add(emp(94L, "EMP-0094", "Poornima", "Rajan", "poornima.rajan@acmecorp.com", "+91-90111-10063",
				"Senior Engineer", "Engineering", "Bengaluru", "Karnataka", "India", 8L, "Vikram Rao", 96000.0,
				LocalDate.of(2020, 10, 19), true));

		// More Engineers under Deepa Menon (id=9)
		list.add(emp(95L, "EMP-0095", "Varun", "Bhatt", "varun.bhatt@acmecorp.com", "+91-90111-10064",
				"Software Engineer", "Engineering", "Hyderabad", "Telangana", "India", 9L, "Deepa Menon", 78000.0,
				LocalDate.of(2021, 5, 3), true));

		list.add(emp(96L, "EMP-0096", "Shweta", "Pandey", "shweta.pandey@acmecorp.com", "+91-90111-10065",
				"Senior Engineer", "Engineering", "Hyderabad", "Telangana", "India", 9L, "Deepa Menon", 94000.0,
				LocalDate.of(2020, 12, 7), true));

		// More Engineers under Amit Joshi (id=10)
		list.add(emp(97L, "EMP-0097", "Sandhya", "Kulkarni", "sandhya.kulkarni@acmecorp.com", "+91-90111-10066",
				"Software Engineer", "Engineering", "Pune", "Maharashtra", "India", 10L, "Amit Joshi", 83000.0,
				LocalDate.of(2021, 1, 11), true));

		list.add(emp(98L, "EMP-0098", "Pradeep", "Jain", "pradeep.jain@acmecorp.com", "+91-90111-10067",
				"Senior Engineer", "Engineering", "Pune", "Maharashtra", "India", 10L, "Amit Joshi", 99000.0,
				LocalDate.of(2020, 7, 27), true));

		// More Support Engineers under Aishwarya Kulkarni (id=30)
		list.add(emp(99L, "EMP-0099", "Yogesh", "Patil", "yogesh.patil@acmecorp.com", "+91-90111-10068",
				"Support Engineer", "Support", "Pune", "Maharashtra", "India", 30L, "Aishwarya Kulkarni", 66000.0,
				LocalDate.of(2021, 4, 19), true));

		list.add(emp(100L, "EMP-0100", "Tina", "Joshi", "tina.joshi@acmecorp.com", "+91-90111-10069",
				"Support Engineer", "Support", "Pune", "Maharashtra", "India", 30L, "Aishwarya Kulkarni", 65000.0,
				LocalDate.of(2021, 9, 6), true));

		return list;
	}

	/**
	 * Factory method to build an {@link Employee} instance from individual fields.
	 * Computes {@code fullName} automatically from first and last name.
	 */
	private static Employee emp(Long id, String empNum, String firstName, String lastName, String email, String phone,
			String designation, String department, String city, String state, String country, Long managerId,
			String managerName, Double salary, LocalDate joiningDate, boolean active) {

		Employee emp = new Employee();

		emp.setId(id);
		emp.setEmployeeNumber(empNum);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setFullName(firstName + " " + lastName);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setDesignation(designation);
		emp.setDepartment(department);
		emp.setCity(city);
		emp.setState(state);
		emp.setCountry(country);
		emp.setManagerId(managerId);
		emp.setManagerName(managerName);
		emp.setSalary(salary);
		emp.setJoiningDate(joiningDate);
		emp.setActive(active);

		return emp;
	}
}
