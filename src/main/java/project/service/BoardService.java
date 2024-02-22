package project.service;

import java.util.List;

import project.entity.board;


public interface BoardService {
    public static final int COUNT_PER_PAGE = 10;
    
	List<board> getBoardList(int page, String field, String query);
	
	board getBoard(int bid);

	int getBoardCount();
	
	void insertBoard(board board);
	
	void updateBoard(board board);
	
	void deleteBoard(int bid);
	
	void increaseViewCount(int bid);
	
	void increaseReplyCount(int bid);

}