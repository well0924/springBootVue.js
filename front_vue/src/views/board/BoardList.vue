<template>
  <div class="board-list">
    <div class="common-buttons">
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnWrite">등록</button>
    </div>
    <table class="w3-table-all">
      <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, idx) in list" :key="idx">
        <td>{{ row.id }}</td>
        <td><a v-on:click="fnView(`${row.id}`)">{{ row.title }}</a></td>
        <td>{{ row.author }}</td>
        <td>{{row.createdTime}}</td>
      </tr>
      </tbody>
    </table>
    <div class="pagination w3-bar w3-padding-16 w3-small" v-if="pageSize>0">
      <span class="pg">
      <a href="javascript:;" @click="fnPage(0)" class="first w3-button w3-border">&lt;&lt;</a>
      <a href="javascript:;" class="prev w3-button w3-border">&lt;</a>
      <template >

      </template>
      <a href="javascript:;" class="next w3-button w3-border">&gt;</a>
      <a href="javascript:;" class="last w3-button w3-border" v-bind:aria-disabled="last===true">&gt;&gt;</a>
      </span>
    </div>
  </div>

  <div>
    <select>
      <option value="">- 선택 -</option>
      <option value="a">작성자</option>
      <option value="t">제목</option>
      <option value="c">내용</option>
    </select>
    &nbsp;
    <input type="text" @keyup.enter="fnPage()">
    &nbsp;
    <button @click="fnPage()">검색</button>
  </div>
</template>

<script>
export default {
  data() { //변수생성
    return {
      requestBody: {}, //리스트 페이지 데이터전송
      list: {}, //리스트 데이터
      no: '', //게시판 숫자처리
      paging:{
        sort:{
          empty : false,
          sorted : true,
          unsorted : false
        },
        offset : 0,
        pageSize : 0,
        pageNumber : 0,
        paged :true,
        unpaged : true
      },
      last : true,
      totalElements: 0,
      totalPages : 0,
      number : 0,
      sort : {
        empty : false,
        sorted : true,
        unsorted : false
      },
      first : false,
      numberOfElements : 3,
      empty : false,
      pageSize: 0,
      startPage:0,
      endPage:0,
      pageNumber:0,
      tempEndPage:0,
      page: 0,
      size: this.pageSize,
      keyword: this.$route.query.keyword,
       paginavigation: function () { //페이징 처리 for문 커스텀
       let pageNumber = this.pageNumber //;
       let start_page = this.startPage;//${T(java.lang.Math).floor(pageNumber/pageSize)*pageSize+1}
       let end_page = this.endPage;//(${tempEndPage < totalPages ? tempEndPage : totalPages})
       for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
       return pageNumber;
       }
    }
  },
  mounted() {
    this.fnGetList()
  },
  methods: {
    fnGetList() {
      this.requestBody = { // 데이터 전송
        keyword: this.keyword,
        page: this.page,
        size: this.size
      }
      //게시글 목록
      this.$axios.get(this.$serverUrl + "/api/board/list", {
        headers: { Authorization : localStorage.getItem('user_token') }
      }).then((res) => {

        this.list = res.data.content  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.
        this.paging = res.data.pageable
        this.pageSize = res.data.size
        this.pageNumber = res.data.pageable.pageNumber
        this.totalPages = res.data.totalPages
        this.first = res.data.first
        this.startPage = Math.floor(this.pageNumber/this.pageSize)*this.pageSize+1
        this.tempEndPage = this.startPage + this.pageSize -1
        this.endPage = this.tempEndPage < this.totalPages ? this.tempEndPage : this.totalPages

      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
      //게시글에 검색어가 있는 경우
    },
    fnView(idx) {//게시글 조회
      this.requestBody.idx = idx
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    fnWrite() {//게시글 작성
      this.$router.push({
        path: './create'
      })
    },
    fnPage(n) {//게시글 페이징
      if (this.page !== n) {
        this.page = n
        this.fnGetList()
      }
    },
    getFistPrevPage(){
      this.requestBody.pageNumber,
      this.router.push({
         path:'./list?page='+(this.pageNumber - this.pageNumber),
         query: this.requestBody
      })
    }
  }
}
</script>

<style scoped>

</style>