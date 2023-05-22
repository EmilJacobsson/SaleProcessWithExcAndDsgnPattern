package se.kth.iv1350.saleprocess.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and updates a sale information list of <code>SaleInfoDTO</code> which contain information about
 * each processed sale. 
 * (Would normally be used to call the external accounting system database to fetch sale
 * information from there.)
 */
public class AccountingRegistry {
	private List<SaleInfoDTO> saleInfoList = new ArrayList<SaleInfoDTO>();
	
	/**
	 * Creates a package private instance.
	 */
	AccountingRegistry() {
		
	}
	
	/**
	 * Adds the specified <code>SaleInfoDTO</code> to the sale information list.
	 * 
	 * @param saleInfo The information of a sale to be stored.
	 */
	public void storeSaleInfo(SaleInfoDTO saleInfo) {
		saleInfoList.add(saleInfo);
	}
}
