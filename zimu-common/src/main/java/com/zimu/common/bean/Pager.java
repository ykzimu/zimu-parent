package com.zimu.common.bean;

import java.util.Collections;
import java.util.List;

import com.zimu.common.utils.BeanUtils;

public class Pager<T> extends Pageable {
	private static final long serialVersionUID = 1L;

	// 数据List
	private List<T> list;
	// 总记录数
	private long total = 0L;
	// 总页数
	private int pages = 0;
	// 当前页的数量
	private int size;
	// 当前页面第一个元素在数据库中的行号
	private int startRow;
	// 当前页面最后一个元素在数据库中的行号
	private int endRow;
	// 前一页
	private int prePage;
	// 下一页
	private int nextPage;
	// 是否为第一页
	private boolean isFirstPage = false;
	// 是否为最后一页
	private boolean isLastPage = false;
	// 是否有前一页
	private boolean hasPreviousPage = false;
	// 是否有下一页
	private boolean hasNextPage = false;
	// 导航页码数
	private int navigatePages;
	// 所有导航页号
	private int[] navigatepageNums;
	// 导航条上的第一页
	private int navigateFirstPage;
	// 导航条上的最后一页
	private int navigateLastPage;

	private long storeId;// storeId条件

	public Pager() {
		super();
	}

	/**
	 * 包装Page对象
	 *
	 * @param pageable
	 */
	public Pager(Pageable pageable) {
		this(pageable, Collections.emptyList(), 0L);
	}

	/**
	 * 包装Page对象
	 *
	 * @param pageable
	 * @param list
	 */
	public Pager(Pageable pageable, List<T> list, long totalCount) {
		this(pageable, list, totalCount, 9);
	}

	/**
	 * 包装Page对象
	 *
	 * @param list
	 */
	public Pager(Pageable pageable, List<T> list, long totalCount, int navigatePages) {
		super();
		if (pageable != null) {
			try {
				BeanUtils.copyProperties(this, pageable);
			} catch (Exception e) {

			}
		}

		// 判断是否是整页
		Long isFull = totalCount % (new Long(pageSize));
		Long cnt = totalCount / (new Long(pageSize));
		if (isFull.intValue() != 0) {
			this.pages = cnt.intValue() + 1;
		} else {
			this.pages = cnt.intValue();
		}

		this.list = list;
		this.size = list.size();
		this.total = totalCount;
		// 由于结果是>startRow的，所以实际的需要+1
		if (this.size == 0) {
			this.startRow = 0;
			this.endRow = 0;
		} else {
			this.startRow = (pageNum - 1) * pageSize + 1;
			// 计算实际的endRow（最后一页的时候特殊）
			this.endRow = this.startRow - 1 + this.size;
		}

		this.navigatePages = navigatePages;
		// 计算导航页
		calcNavigatepageNums();
		// 计算前后页，第一页，最后一页
		calcPage();
		// 判断页面边界
		judgePageBoudary();

	}

	/**
	 * 计算导航页
	 */
	private void calcNavigatepageNums() {
		// 当总页数小于或等于导航页码数时
		if (pages <= navigatePages) {
			navigatepageNums = new int[pages];
			for (int i = 0; i < pages; i++) {
				navigatepageNums[i] = i + 1;
			}
		} else { // 当总页数大于导航页码数时
			navigatepageNums = new int[navigatePages];
			int startNum = pageNum - navigatePages / 2;
			int endNum = pageNum + navigatePages / 2;

			if (startNum < 1) {
				startNum = 1;
				// (最前navigatePages页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			} else if (endNum > pages) {
				endNum = pages;
				// 最后navigatePages页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatepageNums[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 计算前后页，第一页，最后一页
	 */
	private void calcPage() {
		if (navigatepageNums != null && navigatepageNums.length > 0) {
			navigateFirstPage = navigatepageNums[0];
			navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
			if (pageNum > 1) {
				prePage = pageNum - 1;
			}
			if (pageNum < pages) {
				nextPage = pageNum + 1;
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void judgePageBoudary() {
		isFirstPage = pageNum == 1;
		isLastPage = pageNum == pages || pages == 0;
		hasPreviousPage = pageNum > 1;
		hasNextPage = pageNum < pages;
	}

	public Pager(Pager<?> pageable) {
		super();
		try {
			BeanUtils.copyProperties(this, pageable);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public Pager(Integer pageNum, Integer pageSize) {
		super();
		setPageNum(pageNum);
		setPageSize(pageSize);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public int getNavigateFirstPage() {
		return navigateFirstPage;
	}

	public void setNavigateFirstPage(int navigateFirstPage) {
		this.navigateFirstPage = navigateFirstPage;
	}

	public int getNavigateLastPage() {
		return navigateLastPage;
	}

	public void setNavigateLastPage(int navigateLastPage) {
		this.navigateLastPage = navigateLastPage;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

}
