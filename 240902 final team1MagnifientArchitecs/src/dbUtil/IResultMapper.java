package dbUtil;

import java.sql.ResultSet;

public interface IResultMapper<T> {
	
	// 제네릭 T 설정
	T resultMapping(ResultSet rs);
}
