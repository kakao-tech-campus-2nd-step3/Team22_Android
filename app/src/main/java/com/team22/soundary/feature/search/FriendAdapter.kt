import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.databinding.FriendItemBasicBinding
import com.team22.soundary.databinding.FriendItemNewBinding
import com.team22.soundary.feature.search.data.Friend

// FriendAdapter를 ListAdapter로 변환
class FriendAdapter : ListAdapter<Friend, RecyclerView.ViewHolder>(FriendDiffCallback()) {

    private val VIEW_TYPE_NEW = 1
    private val VIEW_TYPE_BASIC = 2

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isNewFriend) {
            VIEW_TYPE_NEW
        } else {
            VIEW_TYPE_BASIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NEW -> {
                // NewFriendViewHolder 생성
                val binding = FriendItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewFriendViewHolder(binding)
            }
            VIEW_TYPE_BASIC -> {
                // BasicFriendViewHolder 생성 (ViewBinding 사용)
                val binding = FriendItemBasicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BasicFriendViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friend = getItem(position) // ListAdapter는 getItem()으로 데이터 접근
        // ViewHolder 타입에 따라 캐스팅하여 bind() 호출
        when (holder) {
            is NewFriendViewHolder -> holder.bind(friend)
            is BasicFriendViewHolder -> holder.bind(friend)
        }
    }

    class NewFriendViewHolder(private val binding: FriendItemNewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            binding.userNameTextview.text = friend.name
            binding.favoriteGenreTextview.text = friend.genre
            binding.profileInitialTextview.text = friend.name.first().toString()

            binding.friendAcceptButton.setOnClickListener {
                // 새로운 친구 수락 로직
            }
            binding.friendDeclineButton.setOnClickListener {
                // 새로운 친구 거절 로직
            }
        }
    }

    class BasicFriendViewHolder(private val binding: FriendItemBasicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            binding.userNameTextview.text = friend.name
            binding.favoriteGenreTextview.text = friend.genre
            binding.profileInitialTextview.text = friend.name.first().toString()

            binding.friendDeleteButton.setOnClickListener {
                // 친구 삭제 로직
            }
        }
    }

    // 새로운 친구 목록 업데이트
    fun updateList(newFriends: List<Friend>) {
        submitList(newFriends) // ListAdapter의 submitList() 사용
    }
    // DiffUtil 콜백 클래스
    class FriendDiffCallback : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            // 고유한 id나 다른 식별자를 사용하여 동일한 항목인지 확인
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            // 데이터가 동일한지 확인
            return oldItem == newItem
        }
    }

}