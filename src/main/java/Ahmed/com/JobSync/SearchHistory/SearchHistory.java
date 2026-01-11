//package Ahmed.com.JobSync.SearchHistory;
//import Ahmed.com.JobSync.user.User;
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "search_history")
//public class SearchHistory {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @ManyToOne
//        @JoinColumn(name = "user_id", nullable = false)
//        private User user;
//
//        @Column(nullable = false)
//        private String query;
//
//        private String location;
//        private Boolean remote;
//
//        @Column(nullable = false)
//        private LocalDateTime  searchedAt = LocalDateTime.now();
//
//        // Getters & setters...
//
//        public Long getId() {
//            return id;
//        }
//
//        public void setId(Long id) {
//            this.id = id;
//        }
//
//        public User getUser() {
//            return user;
//        }
//
//        public void setUser(User user) {
//            this.user = user;
//        }
//
//        public String getQuery() {
//            return query;
//        }
//
//        public void setQuery(String query) {
//            this.query = query;
//        }
//
//        public String getLocation() {
//            return location;
//        }
//
//        public void setLocation(String location) {
//            this.location = location;
//        }
//
//        public Boolean getRemote() {
//            return remote;
//        }
//
//        public void setRemote(Boolean remote) {
//            this.remote = remote;
//        }
//
//        public LocalDateTime getSearchedAt() {
//            return searchedAt;
//        }
//
//        public void setSearchedAt(LocalDateTime searchedAt) {
//            this.searchedAt = searchedAt;
//        }
//    }
//
