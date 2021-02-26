package com.lawencon.laundry.dao.hnative;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.BaseDao;
import com.lawencon.laundry.dao.TransactionDetailsDao;
import com.lawencon.laundry.model.Perfumes;
import com.lawencon.laundry.model.ServiceLaundry;
import com.lawencon.laundry.model.TransactionDetails;
import com.lawencon.laundry.model.Transactions;
import com.lawencon.laundry.repo.TransactionDetailsRepo;

/**
 * @author Dzaky Fadhilla Guci
 */

@Repository(value = "detail_hib")
public class TransactionDetailsDaoHibernateNativeImpl extends BaseDao implements TransactionDetailsDao {

	@Autowired
	private TransactionDetailsRepo transactionDetailsRepo;

	@Override
	public void addTransactionDetails(TransactionDetails transactionDetails) throws Exception {
		String sql = bBuilder(
				"INSERT INTO tb_r_dtl_laundry (dtl_description, unit, service_id, hdr_id, price, perfume_id, status, dtl_code) values ",
				"(?1,?2, (SELECT id FROM tb_m_services WHERE service_code = ?3) , ?4 , ",
				" ?5 * (SELECT service_price FROM tb_m_services WHERE service_code = ?6), ",
				"(SELECT id_perfume FROM tb_m_perfumes WHERE perfume_code = ?7) , 'Received', ?8)").toString();

		em.createNativeQuery(sql).setParameter(1, transactionDetails.getDtlDesc())
				.setParameter(1, transactionDetails.getDtlDesc()).setParameter(2, transactionDetails.getUnit())
				.setParameter(3, transactionDetails.getServiceId().getServiceCode())
				.setParameter(4, transactionDetails.getHdrId().getId()).setParameter(5, transactionDetails.getUnit())
				.setParameter(6, transactionDetails.getServiceId().getServiceCode())
				.setParameter(7, transactionDetails.getPerfumeId().getPerfumeCode())
				.setParameter(8, transactionDetails.getDtlCode()).executeUpdate();

	}

	@Override
	public List<TransactionDetails> getListDetails() throws Exception {
		List<TransactionDetails> listResult = new ArrayList<>();

		String sql = bBuilder("SELECT hdr.laundry_code , dtl.dtl_code ,dtl.dtl_description  , ",
				"dtl.unit , tms.service_code  , tmp.perfume_code , dtl.price, dtl.status FROM tb_r_dtl_laundry dtl ",
				"INNER JOIN tb_r_hdr_laundry hdr ON hdr.id = dtl.hdr_id ",
				"INNER JOIN tb_m_perfumes tmp ON tmp.id_perfume = dtl.perfume_id ",
				"INNER JOIN tb_m_services tms ON tms.id = dtl.service_id ORDER BY hdr.laundry_code").toString();
		List<?> listObj = em.createNativeQuery(sql).getResultList();

		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Transactions trs = new Transactions();
			trs.setLaundryCode((String) objArr[0]);

			TransactionDetails transDetails = new TransactionDetails();
			transDetails.setDtlCode((String) objArr[1]);
			transDetails.setDtlDesc((String) objArr[2]);
			transDetails.setUnit((Double) objArr[3]);

			ServiceLaundry sl = new ServiceLaundry();
			sl.setServiceCode((String) objArr[4]);

			Perfumes p = new Perfumes();
			p.setPerfumeCode((String) objArr[5]);

			transDetails.setPrice((BigDecimal) objArr[6]);
			transDetails.setStatus((String) objArr[7]);

			transDetails.setHdrId(trs);
			transDetails.setServiceId(sl);
			transDetails.setPerfumeId(p);

			listResult.add(transDetails);
		});

		return listResult;
	}

	@Override
	public void updatePickUpTime(TransactionDetails transactionDetails) throws Exception {
		String sql = bBuilder("update tb_r_dtl_laundry set status = 'Picked up' where dtl_code = ? ").toString();
		em.createNativeQuery(sql).setParameter(1, transactionDetails.getDtlCode()).executeUpdate();
	}

	@Override
	public Long getCountData() throws Exception {
		String sql = bBuilder("SELECT count(*) as total_data FROM tb_r_dtl_laundry").toString();
		return Long.valueOf(em.createNativeQuery(sql).getSingleResult().toString());
	}

	@Override
	public TransactionDetails getById(TransactionDetails trx) throws Exception {
		String sql = "UPDATE tb_r_dtl_laundry SET dtl_description = ?1, pickup_time = ?2, status = ?3, unit = ?4, perfume_id = ?5 , service_id = ?6 WHERE id = ?7 ";
		return null;
	}

	@Override
	public void updateData(TransactionDetails trx) throws Exception {
		String sql = "SELECT dtl_code, dtl_description, pickup_time, price, status, unit, hdr_id, perfume_id, service_id FROM tb_r_dtl_laundry WHERE id = ?1 ";

	}
}
