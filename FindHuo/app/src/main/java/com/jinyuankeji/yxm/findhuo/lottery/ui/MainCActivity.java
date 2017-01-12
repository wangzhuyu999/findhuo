package com.jinyuankeji.yxm.findhuo.lottery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.hyphenate.exceptions.HyphenateException;
import com.jinyuankeji.yxm.findhuo.R;
import com.jinyuankeji.yxm.findhuo.base.base_chat.BaseChatActivity;
import com.jinyuankeji.yxm.findhuo.base.base_chat.MyConnectionListener;
import com.jinyuankeji.yxm.findhuo.lottery.runtimepermissions.PermissionsManager;
import com.jinyuankeji.yxm.findhuo.lottery.runtimepermissions.PermissionsResultAction;
import com.jinyuankeji.yxm.findhuo.lottery.ui.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainCActivity extends BaseChatActivity {
    EaseConversationListFragment conversationListFragment;
//    private static EaseContactListFragment contactListFragment;
    private static EMMessageListener emMessageListener;
    private static PersonFragment personFragment;
    private EaseConversationList conversationListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
            }

            @Override
            public void onDenied(String permission) {
            }
        });

        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));
        emMessageListener = new EMMessageListener() {
//
            @Override
            public void onMessageReceived(List<EMMessage> messages) {

                conversationListFragment.refresh();
                EMClient.getInstance().chatManager().importMessages(messages);
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {

            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {

            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {

            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);

    }

    private void initView() {
        setContentView(R.layout.activity_mainc);
        ButterKnife.inject(this);


        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Map<String, EMConversation>> list = new ArrayList<>();
        list.add(conversations);
//        //会话列表控件
//        conversationListView = (EaseConversationList)findViewById(R.id.listv);
////初始化，参数为会话列表集合
//        conversationListView.init(list);
////刷新列表
//        conversationListView.refresh();

//        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
//            @Override
//            public void onContactAgreed(String username) {
////                contactListFragment.refresh();
//            }
//
//            @Override
//            public void onContactRefused(String username) {
//
//            }
//
//            @Override
//            public void onContactInvited(String username, String reason) {
//
//            }
//
//            @Override
//            public void onContactDeleted(String username) {
//                Log.e("","好友被删除了" + username);
//
//                new Thread() {//需要在子线程中调用
//                    @Override
//                    public void run() {
//
////                        contactListFragment.setContactsMap(getContact());
////                        contactListFragment.refresh();
//                    }
//                }.start();
//            }
//
//            @Override
//            public void onContactAdded(String username) {
//                Log.e("添加好友了","添加好友了" + username);
//                new Thread() {//需要在子线程中调用
//                    @Override
//                    public void run() {
////                        contactListFragment.setContactsMap(getContact());
////                        contactListFragment.refresh();
//                    }
//                }.start();
//
//            }
//        });
//
//
        personFragment = new PersonFragment();
////        contactListFragment = new EaseContactListFragment();
//        new Thread() {//需要在子线程中调用
//            @Override
//            public void run() {
//
////                contactListFragment.setContactsMap(getContact());
//            }
//        }.start();
//
//


        conversationListFragment = new EaseConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {

                startActivity(new Intent(MainCActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.fl_chat, conversationListFragment).commit();
    }



//    @OnClick({R.id.tv_chat_list})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_chat_list:
////                conversationListFragment.onResume();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fl_chat, conversationListFragment).commit();
//                break;
//
//        }
//    }

    private Map<String, EaseUser> getContact() {
        Map<String, EaseUser> map = new HashMap<>();
        try {
            List<String> userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();
            for (String userId : userNames) {
                map.put(userId, new EaseUser(userId));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(emMessageListener);
    }
}
