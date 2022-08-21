package com.myapp.lovetest_azuredragon3000.billing;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;

import java.util.List;

public class BillingUltiti {

    public ViewModelBilling viewModelBilling;
    public BillingClientLifecycle billingClientLifecycle;

    public BillingUltiti(ViewModelBilling viewModelBilling,
                         LifecycleOwner lifecycleOwner,
                         BillingClientLifecycle billingClientLifecycle,
                         Activity activity){
        //viewModelBilling = new ViewModelProvider(this).get(activity.class);
        this.viewModelBilling = viewModelBilling;
        //billingClientLifecycle = ((SubApp) activity.getApplication()).getBillingClientLifecycle();
        this.billingClientLifecycle = billingClientLifecycle;
        lifecycleOwner.getLifecycle().addObserver(billingClientLifecycle);

        viewModelBilling.buyEvent.observe(lifecycleOwner, billingFlowParams -> {
            if (billingFlowParams != null) {
                billingClientLifecycle.result = "ready to buy " + billingFlowParams;
                billingClientLifecycle
                        .launchBillingFlow(activity, billingFlowParams);
            }
        });

        // Register purchases when they change.
        billingClientLifecycle.purchaseUpdateEvent.observe(lifecycleOwner, purchases -> {
            if (purchases != null) {
                registerPurchases(purchases);
            }
        });
    }

    public void actionBilling(){
        SkuDetails skuDetails = null;
        if (billingClientLifecycle.skusWithSkuDetails.getValue() != null) {
            skuDetails = billingClientLifecycle.skusWithSkuDetails.getValue().get("10000vang");
            billingClientLifecycle.result = "not null " + skuDetails;
        } else {
            billingClientLifecycle.result = "null";
        }
        assert skuDetails != null;
        BillingFlowParams billingBuilder =
                BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build();
        // Send the parameters to the Activity in order to launch the billing flow.
        viewModelBilling.buyEvent.postValue(billingBuilder);
    }
    public void registerPurchases(List<Purchase> purchaseList) {
        for (Purchase purchase : purchaseList) {
            String sku = purchase.getSkus().get(0);
            String purchaseToken = purchase.getPurchaseToken();
            Log.d("BILLING", "Register purchase with sku: " + sku + ", token: " + purchaseToken);
        }
    }
}
