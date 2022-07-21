<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="content">
		<h1>교과목 정보</h1>

			<div class="table_subject_box">
				<span>교과목_A</span> / 1반 / 2016학년도 / 2학기 
			</div>

			<table class="table_subject">
					<colgroup>
						<col style="width:15%" />
						<col style="width:*px" />
						<col style="width:15%" />
						<col style="width:*px" />
						<col style="width:15%" />
						<col style="width:*px" />
					</colgroup>
					<tr>
						<th>구분</th>
						<td>Off-JT (집체교육)</td>
						<th>학점</th>
						<td>학점형 (3학점)</td>
						<th>담당교수</th>
						<td class="none">담당교수_01</td>
					</tr>
					<tr>
						<th>훈련기간</th>
						<td>2016.09.05 ~ 2016.12.23</td>
						<th>훈련시간</th>
						<td>100시간</td>
						<th>상태</th>
						<td class="none"><input type="button" class="table_blue" value="진행"></td>
					</tr>
					<tr>
						<th>기업명</th>
						<td>-</td>
						<th>기업현장교사</th>
						<td>-</td>
						<th></th>
						<td class="none"></td>
					</tr>
					<tr>
						<th>온라인교과</th>
						<td>있음 (플립러닝)</td>
						<th>선수여부</th>
						<td>필수</td>
						<th>온라인성적비율</th>
						<td class="none">0%</td>
					</tr>
			</table>
			<div class="btn_left"><a href="3_교과목명세서.html"><input type="button" class="orenge" value="교과목명세서" /></a><a href="4_강의계획서.html"><input type="button" class="yellow" value="강의계획서" /></a><a href="5_체크리스트.html"><input type="button" class="grey" value="체크리스트" /></a></div>

		<h1>NCS 능력단위</h1>
			<table class="table">
				<colgroup>
					<col style="width:100px " />
					<col style="width:*px" />
					<col style="width:*px" />
					<col style="width:60px " />
					<col style="width:80px " />
					<col style="width:100px " />
				</colgroup>
				<tr>
					<th>직무명</th>
					<th>능력단위분류코드</th>
					<th>능력단위명</th>					
					<th>수준</th>
					<th>이수시간</th>
					<th class="none">학습모듈</th>
				</tr>
				<tr>
					<td rowspan="2">직무명_01</td>					
					<td>1502010208_14v2</td>
					<td class="left">능력단위_01</td>
					<td></td>
					<td>30</td>
					<td class="none"><input type="radio" name="radio1" value="" checked> 유 <input type="radio" name="radio1" value=""> 무</td>
				</tr>
				<tr>
					<td>1502010208_14v2</td>
					<td class="left">능력단위_01</td>
					<td></td>
					<td>30</td>
					<td class="none"><input type="radio" name="radio2" value="" checked> 유 <input type="radio" name="radio2" value=""> 무</td>
				</tr>
				<tr>
					<td>직무명_02</td>					
					<td>1502010208_14v2</td>
					<td class="left">능력단위_01</td>
					<td></td>
					<td>30</td>
					<td class="none"><input type="radio" name="radio3" value="" checked> 유 <input type="radio" name="radio3" value=""> 무</td>
				</tr>
			</table>

		<h1>교과목 평가정보</h1>
			<table class="table">
				<colgroup>
					<col style="width:*" />
					<col style="width:*" />
					<col style="width:*" />
					<col style="width:*" />
					<col style="width:*" />
					<col style="width:*" />
					<col style="width:*" />
				</colgroup>
				<tr>
					<th>평가방법</th>
					<th>진단평가</th>
					<th>출석</th>
					<th>과제</th>
					<th>중간고사</th>
					<th>기말고사</th>
					<th>직무수행<br />능력평가3차</th>
					<th class="none">평가결과서</th>
				</tr>
				<tr>
					<td class="grey">배점</td>
					<td>-</td>
					<td>10%</td>
					<td>20%</td>
					<td>30%</td>
					<td>30%</td>
					<td>10%</td>
					<td rowspan="3" class="none"><a href="9_평가결과서.html"><input type="button" class="table_search" value="결과조회"></a></td>
				</tr>
				<tr>
					<td class="grey">시행일자</td>
					<td>강의시작<br />1주이내</td>
					<td>-</td>
					<td>-</td>
					<td>2016.10.21(금)</td>
					<td>2016.10.21(금)</td>
					<td>2016.10.21(금)</td>
				</tr>
				<tr>
					<td class="grey">평가결과</td>
					<td><a href="5_체크리스트.html"><input type="button" class="table_search" value="결과조회"></a></td>
					<td></td>
					<td></td>
					<td colspan="3"><a href="8_직무수행능력평가.html"><input type="button" class="table_search" value="결과조회"></a></td>
				</tr>				
			</table>

		<h1>진행율</h1>

		<div class="progress-area mb-040">
			<p>전체 진행율 [ 0 페이지 / 0 페이지 ]</p>
			<ul>
				<li style="width:0%;"><span><b>0</b> %</span></li>
			</ul>
			<p>나의 진행율 [ 0 페이지 / 0 페이지 ]</p>
			<ul>
				<li style="width:0%;"><span><b>0</b> %</span></li>
			</ul>
		</div>

		<h1 style="text-align:left; float:left;">훈련정보</h1>
		<!-- <div class="btn_right"><a href="7_훈련일지.html"><input type="button" class="orenge" value="훈련일자 변경신청" /></a></div> -->
			<table class="table">
					<colgroup>
						<col style="width:50px" />
						<col style="width:*" />
						<col style="width:*" />
						<col style="width:80px" />
						<col style="width:100px" />
						<col style="width:80px" />
						<col style="width:80px" />
						<col style="width:80px" />
						<col style="width:80px" />
					</colgroup>
					<tr>
						<th>주차</th>
						<th>능력단위/능력단위요소</th>
						<th>교육일자</th>
						<th>교육시간</th>
						<th>온라인교과</th>
						<th>팀프로젝트</th>
						<th>학습자료</th>
						<th>훈련일지</th>
						<th class="none">학습활동서</th>
					</tr>					
					<tr>
						<td>1</td>
						<td class="left">능력단위_01<br />능력단위요소_01</td>
						<td>2016.09.10(토) 10:00-12:00<br />2016.09.10(토) 13:00-17:00</td>
						<td>3</td>
						<td>플립러닝 <a href="#" onclick="window.open('study_room.html','popup1','width=925, height=570, left=300, top=150')"><input type="button" class="table_play" value="VIEW" /></a></td>
						<td>1회차</td>
						<td><a href=""><a href="14_학습자료실.html"><input type="button" class="table_btn_grey" value="바로가기" /></a></td>
						<td><a href="7_훈련일지.html"><input type="button" class="table_btn_grey_w50" value="완료"></a></td>
						<td class="none"><a href="6_학습활동서.html"><input type="button" class="table_btn_grey_w50" value="승인"></a></td>
					</tr>
					<tr>
						<td>2</td>
						<td class="left">능력단위_01<br />능력단위요소_01</td>
						<td>2016.09.10(토) 10:00-12:00<br />2016.09.10(토) 13:00-17:00</td>
						<td>3</td>
						<td>플립러닝 <a href="#" onclick="window.open('study_room.html','popup1','width=925, height=570, left=300, top=150')"><input type="button" class="table_play" value="VIEW" /></a></td>
						<td>1회차</td>
						<td><a href=""><a href="14_학습자료실.html"><input type="button" class="table_btn_grey" value="바로가기" /></a></td>
						<td><a href="7_훈련일지.html"><input type="button" class="table_btn_orenge_w50" value="진행"></a></td>
						<td class="none"><a href="6_학습활동서.html"><input type="button" class="table_btn_orenge_w50" value="미승인"></a></td>
					</tr>
					<tr>
						<td>3</td>
						<td class="left">능력단위_01<br />능력단위요소_01</td>
						<td>2016.09.10(토) 10:00-12:00<br />2016.09.10(토) 13:00-17:00</td>
						<td>3</td>
						<td>플립러닝 <a href="#" onclick="window.open('study_room.html','popup1','width=925, height=570, left=300, top=150')"><input type="button" class="table_play" value="VIEW" /></a></td>
						<td>1회차</td>
						<td><a href="14_학습자료실.html"><input type="button" class="table_btn_grey" value="바로가기" /></a></td>
						<td><a href="7_훈련일지.html"><input type="button" class="table_btn_orenge_w50" value="진행"></a></td>
						<td class="none"><a href="6_학습활동서.html"><input type="button" class="table_btn_orenge_w50" value="미승인"></a></td>
					</tr>
			</table>
	</div>

