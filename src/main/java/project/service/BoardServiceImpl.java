package project.service;

import java.util.List;

import project.dao.BoardDao;
import project.entity.Board;

public class BoardServiceImpl implements BoardService{
	private BoardDao bDao = new BoardDao();
	

	@Override
	public List<Board> getBoardList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<Board> list = bDao.getBoardList(field, query, COUNT_PER_PAGE, offset);
		return list;
	}

	@Override
	public Board getBoard(int bid) {
		return bDao.getBoard(bid);
	}

	@Override
	public void insertBoard(Board board) {
		bDao.insertBoard(board);
	}

	@Override
	public void updateBoard(Board board) {
		bDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int bid) {
		bDao.deleteBoard(bid);
	}

	// 이하는 일타 쌍피 코드 : 아래 두 함수는 boardDao의 increaseViewCount를 공유함.
	@Override
	public void increaseViewCount(int bid) {
		bDao.increaseCount("view", bid);
	}

	@Override
	public void increaseReplyCount(int bid) {
		bDao.increaseCount("reply", bid);
	}

	@Override
	public int getBoardCount(String field, String query) {
		return bDao.getBoardCount(field, query);
	}

}