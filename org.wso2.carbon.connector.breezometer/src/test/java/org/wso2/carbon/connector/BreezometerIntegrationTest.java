/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.connector;

import org.testng.Assert;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Breezometer integration test
 */
public class BreezometerIntegrationTest extends ConnectorIntegrationTestBase {
    private Map<String, String> esbRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    @BeforeClass(alwaysRun = true) public void setEnvironment() throws Exception {
        init("breezometer-connector-1.0.0");
        esbRequestHeadersMap.put("Accept-Charset", "UTF-8");
        esbRequestHeadersMap.put("Content-Type", "application/json");
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getRealTimeAirQualityIndexByAddress} integration test with mandatory parameters.")
    public void testGetRealTimeAirQualityIndexByAddressWithMandatoryParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRealTimeAirQualityIndexByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getRealTimeAirQualityIndexByAddress_mandatory.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("location") + "&key=" + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getRealTimeAirQualityIndexByAddress} integration test with optional parameters.")
    public void testGetRealTimeAirQualityIndexByAddressWithOptionalParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRealTimeAirQualityIndexByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getRealTimeAirQualityIndexByAddress_optional.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("location") + "&key=" + connectorProperties.getProperty("apiKey") + "&lang="
                + connectorProperties.getProperty("lang");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getRealTimeAirQualityIndexByAddress} integration test with negative parameters.")
    public void testGetRealTimeAirQualityIndexByAddressWithNegativeParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRealTimeAirQualityIndexByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getRealTimeAirQualityIndexByAddress_negative.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("invalid_location") + "&key=" + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getRealTimeAirQualityIndexByGeoCoordinates} integration test with mandatory parameters.")
    public void testGetRealTimeAirQualityIndexByGeoCoordinatesWithMandatoryParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRealTimeAirQualityIndexByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getRealTimeAirQualityIndexByGeoCoordinates_mandatory.json");
        final String apiEndPoint =
                connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties.getProperty("lat")
                        + "&lon=" + connectorProperties.getProperty("lon") + "&key=" + connectorProperties
                        .getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getRealTimeAirQualityIndexByGeoCoordinates} integration test with optional parameters.")
    public void testGetRealTimeAirQualityIndexByGeoCoordinatesWithOptionalParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRealTimeAirQualityIndexByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getRealTimeAirQualityIndexByGeoCoordinates_optional.json");
        final String apiEndPoint =
                connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties.getProperty("lat")
                        + "&lon=" + connectorProperties.getProperty("lon") + "&key=" + connectorProperties
                        .getProperty("apiKey") + "&lang=" + connectorProperties.getProperty("lang");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getRealTimeAirQualityIndexByGeoCoordinates} integration test with negative parameters.")
    public void testGetRealTimeAirQualityIndexByGeoCoordinatesWithNegativeParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRealTimeAirQualityIndexByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getRealTimeAirQualityIndexByGeoCoordinates_negative.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties
                .getProperty("invalid_lat") + "&lon=" + connectorProperties.getProperty("lon") + "&key="
                + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForASpecificDateByAddress} integration test with mandatory parameters.")
    public void testGetHistoryAirQualityDataForASpecificDateByAddressWithMandatoryParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForASpecificDateByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForASpecificDateByAddress_mandatory.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("location") + "&dateTime=" + connectorProperties.getProperty("dateTime") + "&key="
                + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForASpecificDateByAddress} integration test with optional parameters.")
    public void testGetHistoryAirQualityDataForASpecificDateByAddressWithOptionalParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForASpecificDateByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForASpecificDateByAddress_optional.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("location") + "&dateTime=" + connectorProperties.getProperty("dateTime") + "&key="
                + connectorProperties.getProperty("apiKey") + "&lang=" + connectorProperties.getProperty("lang");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForASpecificDateByAddress} integration test with negative parameters.")
    public void testGetHistoryAirQualityDataForASpecificDateByAddressWithNegativeParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForASpecificDateByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForASpecificDateByAddress_negative.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("invalid_location") + "&dateTime=" + connectorProperties.getProperty("dateTime") + "&key="
                + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForASpecificDateByGeoCoordinates} integration test with mandatory parameters.")
    public void testGetHistoryAirQualityDataForASpecificDateByGeoCoordinatesWithMandatoryParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForASpecificDateByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForASpecificDateByGeoCoordinates_mandatory.json");
        final String apiEndPoint =
                connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties.getProperty("lat")
                        + "&lon=" + connectorProperties.getProperty("lon") + "&dateTime=" + connectorProperties
                        .getProperty("dateTime") + "&key=" + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForASpecificDateByGeoCoordinates} integration test with optional parameters.")
    public void testGetHistoryAirQualityDataForASpecificDateByGeoCoordinatesWithOptionalParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForASpecificDateByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForASpecificDateByGeoCoordinates_optional.json");
        final String apiEndPoint =
                connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties.getProperty("lat")
                        + "&lon=" + connectorProperties.getProperty("lon") + "&dateTime=" + connectorProperties
                        .getProperty("dateTime") + "&key=" + connectorProperties.getProperty("apiKey") + "&lang="
                        + connectorProperties.getProperty("lang");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForASpecificDateByGeoCoordinates} integration test with negative parameters.")
    public void testGetHistoryAirQualityDataForASpecificDateByGeoCoordinatesWithNegativeParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForASpecificDateByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForASpecificDateByGeoCoordinates_negative.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties
                .getProperty("invalid_lat") + "&lon=" + connectorProperties.getProperty("lon") + "&dateTime="
                + connectorProperties.getProperty("dateTime") + "&key=" + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForARangeOfDatesByAddress} integration test with mandatory parameters.")
    public void testGetHistoryAirQualityDataForARangeOfDatesByAddressWithMandatoryParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForARangeOfDatesByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForARangeOfDatesByAddress_mandatory.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("location") + "&start_dateTime=" + connectorProperties.getProperty("start_dateTime")
                + "&end_dateTime=" + connectorProperties.getProperty("end_dateTime") + "&key=" + connectorProperties
                .getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForARangeOfDatesByAddress} integration test with optional parameters.")
    public void testGetHistoryAirQualityDataForARangeOfDatesByAddressWithOptionalParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForARangeOfDatesByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForARangeOfDatesByAddress_optional.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("location") + "&start_dateTime=" + connectorProperties.getProperty("start_dateTime")
                + "&end_dateTime=" + connectorProperties.getProperty("end_dateTime") + "&key=" + connectorProperties
                .getProperty("apiKey") + "&lang=" + connectorProperties.getProperty("lang");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForARangeOfDatesByAddress} integration test with negative parameters.")
    public void testGetHistoryAirQualityDataForARangeOfDatesByAddressWithNegativeParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForARangeOfDatesByAddress");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForARangeOfDatesByAddress_negative.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?location=" + connectorProperties
                .getProperty("invalid_location") + "&start_dateTime=" + connectorProperties
                .getProperty("start_dateTime") + "&end_dateTime=" + connectorProperties.getProperty("end_dateTime")
                + "&key=" + connectorProperties.getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates} integration test with mandatory parameters.")
    public void testGetHistoryAirQualityDataForARangeOfDatesByGeoCoordinatesWithMandatoryParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates_mandatory.json");
        final String apiEndPoint =
                connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties.getProperty("lat")
                        + "&lon=" + connectorProperties.getProperty("lon") + "&start_dateTime=" + connectorProperties
                        .getProperty("start_dateTime") + "&end_dateTime=" + "&key=" + connectorProperties
                        .getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates} integration test with optional parameters.")
    public void testGetHistoryAirQualityDataForARangeOfDatesByGeoCoordinatesWithOptionalParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates_optional.json");
        final String apiEndPoint =
                connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties.getProperty("lat")
                        + "&lon=" + connectorProperties.getProperty("lon") + "&start_dateTime=" + connectorProperties
                        .getProperty("start_dateTime") + "&end_dateTime=" + "&key=" + connectorProperties
                        .getProperty("apiKey") + "&lang=" + connectorProperties.getProperty("lang");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_aqi"),
                apiRestResponse.getBody().getString("breezometer_aqi"));
        Assert.assertEquals(esbRestResponse.getBody().getString("breezometer_color"),
                apiRestResponse.getBody().getString("breezometer_color"));
    }

    @Test(enabled = true, groups = {
            "wso2.esb" }, description = "breezometer {getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates} integration test with negative parameters.")
    public void testGetHistoryAirQualityDataForARangeOfDatesByGeoCoordinatesWithNegativeParameters()
            throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_getHistoryAirQualityDataForARangeOfDatesByGeoCoordinates_negative.json");
        final String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/baqi/?lat=" + connectorProperties
                .getProperty("invalid_lat") + "&lon=" + connectorProperties.getProperty("lon") + "&start_dateTime="
                + connectorProperties.getProperty("start_dateTime") + "&end_dateTime=" + "&key=" + connectorProperties
                .getProperty("apiKey");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }
}