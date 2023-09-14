package com.haushive.tyr1.api;

import java.util.ArrayList;
import java.util.List;

import com.haushive.tyr1.model.domain.Crypto;
import com.haushive.tyr1.model.repository.CryptoRepository;

public class CryptoDisplay {
	
	private static CryptoRepository repository;
    public static void main(String[] args) {
    	List<Crypto> cryptoList = repository.findAll();
    	System.out.print(cryptoList.toString());		
    			
    }
    
//    public static void main(String[] args) {
//        ApiClient defaultClient = Configuration.getDefaultApiClient();
//        defaultClient.setBasePath("https://api.gateio.ws/api/v4");
//
//        SpotApi apiInstance = new SpotApi(defaultClient);
//        List<String> cryptoList = new ArrayList<>();
//    	cryptoList.add("RLC_USDT");
//    	cryptoList.add("ETH_USDT");
//    	cryptoList.add("STAKE_USDT");
//    	cryptoList.add("LRC_USDT");
//    	cryptoList.add("ALCX_USDT");
//    	cryptoList.add("DAI_USDT");
//    	cryptoList.add("LYXE_USDT");
//    	cryptoList.add("UNI_USDT");
//    	cryptoList.add("EWTB_USDT");
//    	cryptoList.add("XSUSHI_USDT");
//    	cryptoList.add("DAI_USDT");
////    	cryptoList.add("STAKE_USDT");
//    	cryptoList.forEach(i -> {
////    		String currencyPair = "BTC_USDT"; // String | Currency pair
//            try {
//                List<Ticker> result = apiInstance.listTickers()
//                            .currencyPair(i)
//                            .execute();
//                System.out.println(result);
//            } catch (GateApiException e) {
//                System.err.println(String.format("Gate api exception, label: %s, message: %s", e.getErrorLabel(), e.getMessage()));
//                e.printStackTrace();
//            } catch (ApiException e) {
//                System.err.println("Exception when calling SpotApi#listTickers");
//                System.err.println("Status code: " + e.getCode());
//                System.err.println("Response headers: " + e.getResponseHeaders());
//                e.printStackTrace();
//            }
//    	});
//    }
    
    public List<String> cryptosToDisplay(){
    	List<String> cryptoList = new ArrayList<>();
    	cryptoList.add("RLC_USDT");
    	cryptoList.add("ETH_USDT");
    	return null;
    }
}
