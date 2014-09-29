package com.quantasnet.qlan.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class QlanDomainBase implements Serializable {

	private static final long serialVersionUID = -5776425607224009071L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
