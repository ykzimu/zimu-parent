package com.zimu.view.user;

import com.zimu.common.view.BaseView;
import com.zimu.common.view.ViewNames;
import com.zimu.common.view.annotation.ViewName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ViewName(ViewNames.USER_LIST)
public class UserListView implements BaseView {
}
