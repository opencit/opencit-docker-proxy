package com.intel.mtwilson.dockerproxy.workflow;

import org.apache.http.HttpStatus;

import com.intel.mtwilson.dockerproxy.common.Constants;
import com.intel.mtwilson.dockerproxy.common.ProxyUtil;
import com.intel.mtwilson.dockerproxy.exception.DockerProxyException;
import com.intel.mtwilson.dockerproxy.vrtm.client.VrtmManager;

public class RestartWorkflow extends BypassWorkflow {

	public RestartWorkflow(String requestMethod, String requestUri, String body) {
		super(requestMethod, requestUri, body);
		// TODO Auto-generated constructor stub
	}

	public RestartWorkflow(String requestMethod, String requestUri) {
		super(requestMethod, requestUri);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * This class only implements the method that extracts the container id from
	 * response of the engine and then updates vrtm
	 * 
	 */
	@Override
	public void processResponseFromEngine() throws DockerProxyException {
		int responseStatusInt = Integer.parseInt(responseStatus);

		if (responseStatusInt == HttpStatus.SC_NO_CONTENT) {
			String containerId = ProxyUtil.extractContainerIdformRestartRequest(requestUri);
			VrtmManager vrtmManager = new VrtmManager();
			vrtmManager.checkAndUpdateVrtm(containerId, Constants.VRTM_STATUS_STARTED);
		}
	}

}
