package com.devuoon.backboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingDto {

  private int pageSize;

  private int page;

  private int totalPage;

  private int totalPageNum;   // 총 페이지 수

  private long totalListSize;

  private int startPage;

  private int endPage;

  private int startIndex; // 시작하는 인덱스 번호

  private int block; // 끝나는 인덱스 번호

  private int totalBlockNum;  // 총 블럭 수 

  private int prevBlock;

  private int nextBlock;
  
  // 전체 리스트크기와, 현재 페이지와, 페이지마다 나타낼 글 갯수, 블럭수를 가지고
  // 필요 변수들의 값을 계산하는 생성자
  public PagingDto(Long totalListSize, Integer page,Integer pageSize, Integer blockSize) {
    this.pageSize = pageSize;
    this.page = page;
    this.totalListSize = totalListSize;
    // 변수값 계산
    // 전체 블럭 수 계산
    this.totalPageNum = (int)Math.ceil(this.totalPageNum * 1.0 / this.pageSize);
    // 현재 블럭 계산
    this.block = (int)Math.ceil((this.page) * 1.0 / blockSize);
    // 현재 블럭 시작페이지
    this.startPage = ((this.block - 1) * blockSize + 1);
    // 현재블럭 마지막페이지
    this.endPage = this.startPage + blockSize - 1;
    // 블럭 마지막페이지 검증()
    if (this.endPage > this.totalPageNum) this.endPage = this.totalBlockNum;
    // 이전 블럭(클릭 시, 이전 블럭 마지막 페이지)
    this.prevBlock = (this.block * blockSize) - blockSize;
    // 이전 블럭 검증
    if (this.prevBlock < 1) this.prevBlock = 1;
    // 다음 블럭 검증
    if (this.nextBlock > this.totalPageNum) this.nextBlock = this.totalPageNum;    
    // 시작 인덱스 번호
    this.startIndex = (this.page - 1) * this.pageSize;
    
  }
}
