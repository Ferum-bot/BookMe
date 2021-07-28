package com.levit.book_me.ui.activities.main_screen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.levit.book_me.core_network.model.enums.NetworkStatus
import com.levit.book_me.databinding.ActivityMainScreenBinding
import com.levit.book_me.di.components.MainScreenComponent
import com.levit.book_me.ui.base.BaseActivity
import com.levit.bookme.uikit.models.NotificationManager
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

class MainScreenActivity:
    BaseActivity() {

    companion object {

        private const val CURRENT_VIEW_PAGER_PAGE_NAME = "current_view_pager_page"
    }

    private lateinit var binding: ActivityMainScreenBinding

    private val viewModel: MainScreenActivityViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    private val onPageChangedCallback = object: ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            when(position) {
                MainScreenViewPagerAdapter.CHATS_FRAGMENT_POSITION ->
                    chatsButtonClicked()
                MainScreenViewPagerAdapter.CURRENT_FRIEND_FRAGMENT_POSITION ->
                    currentFriendButtonClicked()
                MainScreenViewPagerAdapter.PROFILE_FRAGMENT_POSITION ->
                    profileButtonClicked()
            }
        }
    }

    lateinit var mainScreenComponent: MainScreenComponent

    private val connectionLostManager by lazy {
        NotificationManager(lifecycle, binding.notificationConnectionLost.root)
    }

    private val connectionRestoredManager by lazy {
        NotificationManager(lifecycle, binding.notificationConnectionRestored.root)
    }

    private val pagerAdapter by lazy {
        MainScreenViewPagerAdapter(this)
    }

    private val networkStatus: SharedFlow<NetworkStatus>
    get() = bookMeApp.networkMonitor.isNetworkAvailable

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()

        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureLayout()
        setAllClickListeners()
        setAllObservers()
        startListeningNetworkMonitor()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_VIEW_PAGER_PAGE_NAME, binding.viewPager.currentItem)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val currentViewPagerPage = savedInstanceState.getInt(
            CURRENT_VIEW_PAGER_PAGE_NAME, MainScreenViewPagerAdapter.PROFILE_FRAGMENT_POSITION,
        )

        binding.viewPager.currentItem = currentViewPagerPage
    }

    private fun initComponent() {
        mainScreenComponent = appComponent.mainScreenComponent().build()
        mainScreenComponent.inject(this)
    }

    private fun configureLayout() {
        val viewPagerAdapter = pagerAdapter

        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.registerOnPageChangeCallback(onPageChangedCallback)

        binding.checkedChatsActionButton.rippleColor = Color.GRAY
        binding.notCheckedChatsActionButton.rippleColor = Color.GRAY
        binding.checkedCurrentFriendProfileActionButton.rippleColor = Color.GRAY
        binding.notCheckedCurrentFriendProfileActionButton.rippleColor = Color.GRAY
        binding.checkedProfileActionButton.rippleColor = Color.GRAY
        binding.notCheckedProfileActionButton.rippleColor = Color.GRAY
    }

    private fun setAllClickListeners() {
        binding.checkedProfileActionButton.setOnClickListener {
            profileButtonClicked()
        }
        binding.notCheckedProfileActionButton.setOnClickListener {
            profileButtonClicked()
        }

        binding.checkedChatsActionButton.setOnClickListener {
            chatsButtonClicked()
        }
        binding.notCheckedChatsActionButton.setOnClickListener {
            chatsButtonClicked()
        }

        binding.checkedCurrentFriendProfileActionButton.setOnClickListener {
            currentFriendButtonClicked()
        }
        binding.notCheckedCurrentFriendProfileActionButton.setOnClickListener {
            currentFriendButtonClicked()
        }
    }

    private fun setAllObservers() {
        viewModel.currentInterlocutorId.observe(this) { interlocutorId ->
            if (interlocutorId == null) {
                return@observe
            }
            pagerAdapter.openInterlocutorProfile(interlocutorId)
            viewModel.interlocutorProfileOpened()
        }

        viewModel.chatAndInterlocutorId.observe(this) { pair ->
            if (pair == null) {
                return@observe
            }
            val chatId = pair.first
            val interlocutorId = pair.second
            pagerAdapter.openCurrentChat(chatId, interlocutorId)
            viewModel.currentChatOpened()
        }

        viewModel.openGeneralChat.observe(this) { openChats ->
            if (openChats) {
                pagerAdapter.openGeneralChats()
            }
            viewModel.generalChatHasOpened()
        }

    }

    private fun startListeningNetworkMonitor() {
        lifecycleScope.launchWhenStarted {
            networkStatus.collect(this@MainScreenActivity::handleNewNetworkStatus)
        }
    }

    private fun profileButtonClicked() {
        setAllButtonsNotChecked()

        binding.checkedProfileActionButton.visibility = View.VISIBLE
        binding.notCheckedProfileActionButton.visibility = View.INVISIBLE

        binding.viewPager.currentItem = MainScreenViewPagerAdapter.PROFILE_FRAGMENT_POSITION
    }

    private fun chatsButtonClicked() {
        setAllButtonsNotChecked()

        binding.checkedChatsActionButton.visibility = View.VISIBLE
        binding.notCheckedChatsActionButton.visibility = View.INVISIBLE

        binding.viewPager.currentItem = MainScreenViewPagerAdapter.CHATS_FRAGMENT_POSITION
    }

    private fun currentFriendButtonClicked() {
        setAllButtonsNotChecked()

        binding.checkedCurrentFriendProfileActionButton.visibility = View.VISIBLE
        binding.notCheckedCurrentFriendProfileActionButton.visibility = View.INVISIBLE

        binding.viewPager.currentItem = MainScreenViewPagerAdapter.CURRENT_FRIEND_FRAGMENT_POSITION
    }

    private fun setAllButtonsNotChecked() {
        binding.notCheckedProfileActionButton.visibility = View.VISIBLE
        binding.notCheckedCurrentFriendProfileActionButton.visibility = View.VISIBLE
        binding.notCheckedChatsActionButton.visibility = View.VISIBLE

        binding.checkedProfileActionButton.visibility = View.INVISIBLE
        binding.checkedCurrentFriendProfileActionButton.visibility = View.INVISIBLE
        binding.checkedChatsActionButton.visibility = View.INVISIBLE
    }

    private fun handleNewNetworkStatus(newStatus: NetworkStatus) = when(newStatus) {
        NetworkStatus.NETWORK_AVAILABLE -> {
            connectionRestoredManager.showNotification()
        }
        NetworkStatus.NETWORK_LOST -> {
            connectionLostManager.showNotification()
        }
    }
}