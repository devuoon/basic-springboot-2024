export function formatDate(date) {  // 2024-07-02T12:28:21.178428
  var result = date.replace('T', ' '); // T를 공백으로 변경
  var index = result.lastIndexOf(' '); // 초 앞에 있는 : 위치 찾기

  result = result.substr(0, index);

  return result;
}