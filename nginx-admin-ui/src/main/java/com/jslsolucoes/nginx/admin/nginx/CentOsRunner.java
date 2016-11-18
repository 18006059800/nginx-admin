/*******************************************************************************
 * Copyright 2016 JSL Solucoes LTDA - https://jslsolucoes.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.jslsolucoes.nginx.admin.nginx;

import javax.enterprise.inject.Vetoed;

import com.jslsolucoes.nginx.admin.os.OperationalSystemDistribution;
import com.jslsolucoes.nginx.admin.runtime.RuntimeResult;
import com.jslsolucoes.nginx.admin.runtime.RuntimeResultType;
import com.jslsolucoes.nginx.admin.runtime.RuntimeUtils;

@Vetoed
public class CentOsRunner implements Runner {

	@Override
	public OperationalSystemDistribution distro() {
		return OperationalSystemDistribution.CENTOS;
	}

	@Override
	public RuntimeResult start(NginxConfiguration nginxConfiguration) {
		return RuntimeUtils.command(nginxConfiguration.getBin().getAbsolutePath() + " -c "
				+ nginxConfiguration.getConf().getAbsolutePath());
	}

	@Override
	public RuntimeResult stop(NginxConfiguration nginxConfiguration) {
		return RuntimeUtils.command(nginxConfiguration.getBin().getAbsolutePath() + " -s quit");
	}

	@Override
	public RuntimeResult restart(NginxConfiguration nginxConfiguration) {
		stop(nginxConfiguration);
		start(nginxConfiguration);
		return status(nginxConfiguration);
	}

	@Override
	public RuntimeResult status(NginxConfiguration nginxConfiguration) {
		return new RuntimeResult(RuntimeResultType.SUCCESS,"Process checking ...");
	}

}