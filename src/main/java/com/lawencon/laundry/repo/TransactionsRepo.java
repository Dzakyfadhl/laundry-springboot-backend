package com.lawencon.laundry.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.model.Transactions;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {

	@Modifying
	@Query(value = "UPDATE tb_r_hdr_laundry SET total_price = (SELECT SUM(price) FROM tb_r_dtl_laundry where hdr_id = ?1 group by hdr_id) WHERE id = ?2 ", nativeQuery = true)
	void updateTotalPrice(Long first, Long second) throws Exception;

	@Query(value = "SELECT hdr.laundry_code, hdr.total_price, hdr.laundry_time , c.customer_code, c.fullname, c.phone, c.address FROM tb_r_hdr_laundry hdr INNER JOIN tb_m_customers c on c.id = hdr.customer_id ", nativeQuery = true)
	List<Object[]> getAllTransactions() throws Exception;

	@Query(value = "SELECT * FROM tb_r_hdr_laundry h INNER JOIN tb_m_profiles p on p.id = h.payments_id INNER JOIN tb_m_customers  c ON c.id = h.customer_id INNER JOIN tb_m_payments_type pt ON pt.id = h.payments_id", nativeQuery = true)
	List<Transactions> getAllTransactionss() throws Exception;

	@Modifying
	@Query(value = "UPDATE tb_r_hdr_laundry SET customer_id = ?1 , employee_id = ?2 , payments_id=?3  WHERE id = ?4", nativeQuery = true)
	void updateData(Long custId, Long empId, Long payId, Long id) throws Exception;

	@Query(value = "SELECT * FROM tb_r_hdr_laundry WHERE id = ?1", nativeQuery = true)
	Transactions getById(Long id) throws Exception;
}
