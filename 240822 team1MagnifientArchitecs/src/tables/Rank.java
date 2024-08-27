package tables;

import java.util.Objects;

public class Rank {
	// 다 완성되었을 때 마감하면 보유 금액을 DB에 저장해서
	// 랭킹을 확인할 수 있도록 만든 테이블
	
	private String rank_ID;
	private String rank_Money;
	private String rank_Rank;

	public Rank(String rank_ID, String rank_Money, String rank_Rank) {
		super();
		this.rank_ID = rank_ID;
		this.rank_Money = rank_Money;
		this.rank_Rank = rank_Rank;
	}

	public String getRank_ID() {
		return rank_ID;
	}

	public void setRank_ID(String rank_ID) {
		this.rank_ID = rank_ID;
	}

	public String getRank_Money() {
		return rank_Money;
	}

	public void setRank_Money(String rank_Money) {
		this.rank_Money = rank_Money;
	}

	public String getRank_Rank() {
		return rank_Rank;
	}

	public void setRank_Rank(String rank_Rank) {
		this.rank_Rank = rank_Rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank_ID, rank_Money, rank_Rank);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Rank)) {
			return false;
		}
		Rank other = (Rank) obj;
		return Objects.equals(rank_ID, other.rank_ID) && Objects.equals(rank_Money, other.rank_Money)
				&& Objects.equals(rank_Rank, other.rank_Rank);
	}

	@Override
	public String toString() {
		return "Rank [rank_ID=" + rank_ID + ", rank_Money=" + rank_Money + ", rank_Rank=" + rank_Rank + "]";
	}

}
