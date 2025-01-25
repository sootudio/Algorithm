from sys import stdin, setrecursionlimit
from functools import lru_cache

input = stdin.readline
setrecursionlimit(10 ** 6)

# 입력 처리
n, m = map(int, input().split())
board = [input().rstrip() for _ in range(n)]

# 상하좌우 방향
DIRS = [(1, 0), (0, 1), (-1, 0), (0, -1)]

# 비트마스크를 통해 알파벳을 숫자로 변환
def to_bit(c): 
    return 1 << (ord(c) - ord('A'))

# DFS와 메모이제이션
@lru_cache(None)  # 캐싱을 위한 데코레이터
def dfs(r, c, trace):
    # 최대 경로 길이
    max_length = 0
    
    # 상하좌우로 이동
    for dr, dc in DIRS:
        nr, nc = r + dr, c + dc
        if 0 <= nr < n and 0 <= nc < m:  # 범위 체크
            value = to_bit(board[nr][nc])
            if not (trace & value):  # 방문하지 않은 알파벳만 이동
                max_length = max(max_length, dfs(nr, nc, trace | value))
    
    # 현재 위치까지 포함한 최대 길이
    return max_length + 1

# 초기 상태: (0, 0)에서 시작
start_bit = to_bit(board[0][0])
result = dfs(0, 0, start_bit)

# 결과 출력
print(result)
