package tables;

import java.util.Objects;

public class RankStock {
	// 다 완성되었을 때 마감하면 보유 금액을 DB에 저장해서
	// 랭킹을 확인할 수 있도록 만든 테이블

	private String rank_ID;
	private String rank_Money;
	public RankStock(String rank_ID, String rank_Money) {
		super();
		this.rank_ID = rank_ID;
		this.rank_Money = rank_Money;
	}

	

}
