package project.service;

import java.util.List;

import project.dao.BoardDao;
import project.entity.board;

public class BoardServiceImpl implements BoardService{
	private BoardDao bDao = new BoardDao();
	

	@Override
	public List<board> getBoardList(int page, String field, String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		List<board> list = bDao.getBoardList(field, query, COUNT_PER_PAGE, offset);
		return list;
	}

	@Override
	public board getBoard(int bid) {
		return null;
	}

	@Override
	public int getBoardCount() {
		return bDao.getBoardCount();
	}

	@Override
	public void insertBoard(board board) {
	}

	@Override
	public void updateBoard(board board) {
	}

	@Override
	public void deleteBoard(int bid) {
	}

	@Override
	public void increaseViewCount(int bid) {
	}

	@Override
	public void increaseReplyCount(int bid) {
	}

}