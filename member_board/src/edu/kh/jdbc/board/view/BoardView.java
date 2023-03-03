package edu.kh.jdbc.board.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.service.CommentService;
import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.model.vo.Comment;
import edu.kh.jdbc.main.view.MainView;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	
	private BoardService bService = new BoardService();
	
	private CommentService cService = new CommentService();
	
	
	
	/**
	 * 게시판 기능 메뉴 화면
	 */
	public void boardMenu() {
		
		int input = -1;
		
		do {
			try {
				System.out.println("\n***** 게시판 기능 *****\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 검색");
				System.out.println("0. 로그인 메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행 문자 제거
				
				System.out.println();
				
				switch(input) {
				case 1: selectAllBoard();  break; // 게시글 목록 조회
				case 2: selectBoard(); break; // 게시글 상세 조회
				case 3: insertBoard(); break; // 게시글 등록(삽입)
				
				case 4:	searchBoard(); break;
				
				
				case 0: System.out.println("[로그인 메뉴로 이동합니다.]"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력 해주세요.");
				}
				
				System.out.println();
				
			}catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
			
		}while(input != 0);
		
	}
	
	
	/**
	 * 게시글 목록 조회
	 */
	private void selectAllBoard() {
		System.out.println("\n[게시글 목록 조회]\n");
		
		try {
			
			List<Board> boardList = bService.selectAllBoard();
			// -> DAO에서 new ArrayList<>(); 구문으로 인해 반환되는 조회 결과는
			//    null이 될 수 없다!!!
			
			if(boardList.isEmpty()) { // 조회 결과가 없을 경우
				System.out.println("게시글이 존재하지 않습니다.");
				
			}else {
				
				for(Board b : boardList) {
					// 3 | 샘플 제목3[4] | 유저삼 | 3시간 전 | 10
					System.out.printf("%d | %s[%d] | %s | %s | %d\n",
							b.getBoardNo(), 
							b.getBoardTitle(), 
							b.getCommentCount(),
							b.getMemberName(),
							b.getCreateDate(),
							b.getReadCount());
				}
			}
			
			
		}catch(Exception e){
			System.out.println("\n<<게시글 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 게시글 상세 조회
	 */
	private void selectBoard() {
		System.out.println("\n[게시글 상세 조회]\n");
		
		try {
			System.out.print("게시글 번호 입력 : ");
			int boardNo = sc.nextInt();
			sc.nextLine(); 
			
			// 게시글 상세 조회 서비스 호출 후 결과 반환 받기
			Board board = bService.selectBoard(boardNo, MainView.loginMember.getMemberNo());
											// 게시글 번호, 로그인한 회원의 회원 번호(자신의 글 조회수증가 X)
			
		} catch(Exception e) {
			System.out.println("\n<<게시글 상세 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	
	/** 게시글 상세조회 시 출력되는 서브 메뉴
	 * @param board(상세조회된 게시글 + 작성자 번호 + 댓글 목록)
	 */
	private void subBoardMenu(Board board) {
		
		
	}
	
	
	/** 댓글 등록
	 * @param bNo
	 * @param mNo
	 */
	private void insertComment(int bNo, int mNo) {
		
		
	
	}
	
	/**
	 *  내용 입력 
	 *  @return content
	 */
	private String inputContent() {
		String content = ""; // 빈 문자열
		String input = null; // 참조하는 객체가 없음
		
		System.out.println("입력 종료 시 ($exit) 입력");
		
		while(true) {
			input = sc.nextLine();
			
			if(input.equals("$exit")) {
				break;
			}
			
			// 입력된 내용을 content에 누적
			content += input + "\n"; 
		}
		
		return content;
	}
	
	
	/** 댓글 수정
	 * @param commentList
	 * @param memberNo
	 */
	private void updateComment(List<Comment> commentList, int memberNo) {
		
	
		
	}
	
	
	/** 댓글 삭제
	 * @param commentList
	 * @param memberNo
	 */
	private void deleteComment(List<Comment> commentList, int memberNo) {
		
		
		
	}
	
	
	/** 게시글 수정
	 * @param boardNo
	 */
	private void updateBoard(int boardNo) {
		
	
		
	}
	
	
	
	/** 게시글 삭제
	 * @param boardNo
	 */
	private void deleteBoard(int boardNo) {
		
	}
	
	
	/**
	 * 게시글 등록(삽입)
	 */
	private void insertBoard() {
		
		try {
			System.out.println("\n[게시글 등록]\n");
			
			System.out.print("제목 : ");
			String boardTitle = sc.nextLine();
			
			System.out.println("내용 : ");
			String boardContent = inputContent();
			
			// Board 객체에 제목, 내용, 회원 번호를 담아서 서비스에 전달
			Board board = new Board();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setMemberNo(MainView.loginMember.getMemberNo());
			
			
			int result = bService.insertBoard(board);
			// 0 또는 생성된 게시글 번호(0 초과)
			
	
			if(result > 0) {
				System.out.println("\n[게시글 등록되었습니다.]\n");
				
				// 게시글 상세 조회 서비스 호출 후 결과 반환받기
				Board b = bService.selectBoard(result, MainView.loginMember.getMemberNo());
									// 게시글번호,  로그인한 회원의 회원 번호
									//				-> 자신의 글 조회수 증가 X
				
				/*
				if() {
					
					
					
					
				} else {
					System.out.println();
				}
				*/
				
				
			} else {
				System.out.println("\n[게시글 등록 실패!]\n");
			}
			
			
		}catch(Exception e) {
			System.out.println("\n<<게시글 등록 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 게시글 검색
	 */
	private void searchBoard() {
		try {
			System.out.println("\n[게시글 검색]\n");
			
			System.out.println("1) 제목");
			System.out.println("2) 내용");
			System.out.println("3) 제목 + 내용");
			System.out.println("4) 작성자");
			System.out.print("검색 조건 선택 : ");
			
			int condition = sc.nextInt();
			sc.nextLine();
			
			if(condition >= 1 && condition <= 4) { // 정상 입력
				
				System.out.print("검색어 입력 : ");
				String query = sc.nextLine();
				
				// 검색 서비스 호출 후 결과 반환 받기
				List<Board> boardList = bService.searchBoard(condition, query);
						
				if(boardList.isEmpty()) { // 조회 결과가 없을 경우
					System.out.println("\n[검색 결과가 없습니다.]\n");
					
				}else {
					
					for(Board b : boardList) {
						// 3 | 샘플 제목3[4] | 유저삼 | 3시간 전 | 10
						System.out.printf("%d | %s[%d] | %s | %s | %d\n",
								b.getBoardNo(), 
								b.getBoardTitle(), 
								b.getCommentCount(),
								b.getMemberName(),
								b.getCreateDate(),
								b.getReadCount());
					}
				}
				
				
				
			} else { // 비정상 입력
				System.out.println("\n[1~4번 사이의 숫자를 입력해주세요]\n");
			}
			
			
		}catch (Exception e) {
			System.out.println("\n<<게시글 검색 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	

}
