package com.sxt.entity;

import java.io.Serializable;

public class Rank implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer rankId;
	private String rankName;
	public Integer getRankId() {
		return rankId;
	}
	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
}
