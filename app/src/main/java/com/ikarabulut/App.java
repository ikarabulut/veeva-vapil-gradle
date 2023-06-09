/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.ikarabulut;

import com.veeva.vault.vapil.api.client.VaultClient;
import com.veeva.vault.vapil.api.client.VaultClientBuilder;
import com.veeva.vault.vapil.api.client.VaultClientId;
import com.veeva.vault.vapil.api.model.response.QueryResponse;
import com.veeva.vault.vapil.api.request.QueryRequest;

public class App {


    public static void main(String[] args) {
        // Set the Vault Client Id, which is required for all API calls
        VaultClientId vaultClientId = new VaultClientId("test company","IT","devops",true,"vapil-test");
        // Instantiate the VAPIL VaultClient using user name and password authentication
        VaultClient vaultClient = VaultClientBuilder
                .newClientBuilder(VaultClient.AuthenticationType.BASIC)
                .withVaultDNS("verteobiotech.veevavault.com")
                .withVaultUsername("username@verteobiotech.com")
                .withVaultPassword("password")
                .withVaultClientId(vaultClientId)
                .build();
        // Perform a VQL query and display the results
        QueryResponse resp = vaultClient.newRequest(QueryRequest.class)
                .query("SELECT name__v, email__sys FROM user__sys MAXROWS 3");
        if (resp != null) {
            System.out.println("-----------------------------------------");
            System.out.println("Response Status = " + resp.getResponseStatus());
            System.out.println("Total Records = " + resp.getData().size());
            for (QueryResponse.QueryResult row : resp.getData())
                System.out.println("Name = " + row.get("name__v") + ", Email = " + row.get("email__sys"));
        }
    }
}
