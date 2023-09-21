package com.example.proiectcolectiv.controller;
import com.example.proiectcolectiv.model.*;
import com.example.proiectcolectiv.repository.*;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("")
public class PostController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private DislikesRepository dislikesRepository;
    @Autowired
    private InterestRepository interestRepository;
    @Autowired
    private BadgesRepository badgeRepository;


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/questions/{start}/{end}")
    @Transactional
    public Object getPost(@PathVariable("start") Integer start, @PathVariable("end") Integer end) {
        List<QuestionPremium> result = new ArrayList<>();
        try {
            List<Post> posts = postRepository.getQuestion();
            Integer contor = 0;
            for (Post i : posts) {
                if (contor >= start && contor <= end) {
                    QuestionPremium q = new QuestionPremium();
                    q.setQuestionID(i.getPostID());
                    UserNewAdd u = new UserNewAdd();
                    u.setUid(i.getUserID());
                    if(userRepository.getUserDisplayName(i.getUserID())==null){
                        u.setDisplayName("");
                    }
                    else{
                        u.setDisplayName(userRepository.getUserDisplayName(i.getUserID()));
                    }
                    if(userRepository.getUserURL(i.getUserID())==null){
                        u.setPhotoURL("");
                    }
                    else{
                        u.setPhotoURL(userRepository.getUserURL(i.getUserID()));
                    }

                    q.setUser(u);
                    List<String> label = labelRepository.getPostbyID(i.getPostID());
                    q.setQuestionLabels(label);
                    if(i.getQuestionContent()==null){
                        q.setQuestionContent("");
                    }
                    else{
                        q.setQuestionContent(i.getQuestionContent());
                    }
                    if(i.getQuestionTitle()==null){
                        q.setQuestionTitle("");
                    }
                    else{
                        q.setQuestionTitle(i.getQuestionTitle());
                    }
                    if(likesRepository.getLikePerPost(i.getPostID())==null){
                        q.setLikes(0);
                    }
                    else{
                        q.setLikes(likesRepository.getLikePerPost(i.getPostID()));
                    }
                    if(dislikesRepository.getDislikePerPost(i.getPostID())==null){
                        q.setDislikes(0);

                    }
                    else{
                        q.setDislikes(dislikesRepository.getDislikePerPost(i.getPostID()));
                    }
                    if(i.getCategory()==null){
                        q.setCategory("");
                    }
                    else{
                        q.setCategory(i.getCategory());
                    }
                    if(i.getQuestionCode()==null){
                        q.setCode("");
                    }
                    else{
                        q.setCode(i.getQuestionCode());
                    }

                    List<Comment> comment = commentRepository.getPostbyID(i.getPostID());
                    List<Answer2> answers = new ArrayList<>();
                    for (Comment c : comment) {
                        Answer2 answer = new Answer2();

                        if(c.getCommentCode()==null){
                            answer.setCode("");
                        }
                        else{
                            answer.setCode(c.getCommentCode());
                        }

                        UserNewAdd user1 = new UserNewAdd();
                        if(commentRepository.getCommUserID(c.getCommentID())==null){
                                user1.setUid("");
                        }else{
                            user1.setUid(commentRepository.getCommUserID(c.getCommentID()));
                        }
                        if(userRepository.getUserURL(c.getUserID())==null){
                            user1.setPhotoURL("");
                        }
                        else{
                            user1.setPhotoURL(userRepository.getUserURL(c.getUserID()));
                        }
                        if(userRepository.getUserDisplayName(c.getUserID())==null){
                            user1.setDisplayName("");
                        }
                        else{
                            user1.setDisplayName(userRepository.getUserDisplayName(c.getUserID()));

                        }
                        answer.setUser(user1);
                        if(c.getCommentTitle()==null){
                            answer.setAnswerTitle("");
                        }
                        else{
                            answer.setAnswerTitle(c.getCommentTitle());
                        }
                        if(c.getContent()==null){
                            answer.setAnswerDetails("");
                        }
                        else{
                            answer.setAnswerDetails(c.getContent());
                        }

                        answers.add(answer);
                    }
                    q.setAnswers(answers);
                    result.add(q);

                }
                contor++;
                if (contor > end)
                    break;
            }
            if(result.isEmpty()){
                throw  new NoSuchElementException("Nu-i element") ;
            }
            List<Object> l=new ArrayList<>();
            l.addAll(result);
            JsonFormat x=new JsonFormat(l);
            return x;

        } catch (Exception e) {
            return new ResponseEntity<>(new Eroare(),HttpStatus.NOT_FOUND);
        }

    }



    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/questions/{start}/{end}/{category}")
    @Transactional
    public Object getPostByCategory(@PathVariable("start") Integer start, @PathVariable("end") Integer end,@PathVariable ("category") String category) {
        List<QuestionPremium> result = new ArrayList<>();
        try {
            List<Post> posts = postRepository.getPostariByCategory(category);
            Integer contor = 0;
            for (Post i : posts) {
                if (contor >= start && contor <= end) {
                    QuestionPremium q = new QuestionPremium();
                    q.setQuestionID(i.getPostID());
                    UserNewAdd u = new UserNewAdd();
                    u.setUid(i.getUserID());
                    if(userRepository.getUserDisplayName(i.getUserID())==null){
                        u.setDisplayName("");
                    }
                    else{
                        u.setDisplayName(userRepository.getUserDisplayName(i.getUserID()));
                    }
                    if(userRepository.getUserURL(i.getUserID())==null){
                        u.setPhotoURL("");
                    }
                    else{
                        u.setPhotoURL(userRepository.getUserURL(i.getUserID()));
                    }

                    q.setUser(u);
                    List<String> label = labelRepository.getPostbyID(i.getPostID());
                    q.setQuestionLabels(label);
                    if(i.getQuestionContent()==null){
                        q.setQuestionContent("");
                    }
                    else{
                        q.setQuestionContent(i.getQuestionContent());
                    }
                    if(i.getQuestionTitle()==null){
                        q.setQuestionTitle("");
                    }
                    else{
                        q.setQuestionTitle(i.getQuestionTitle());
                    }

                    if(likesRepository.getLikePerPost(i.getPostID())==null){
                        q.setLikes(0);
                    }
                    else{
                        q.setLikes(likesRepository.getLikePerPost(i.getPostID()));
                    }
                    if(dislikesRepository.getDislikePerPost(i.getPostID())==null){
                        q.setDislikes(0);

                    }
                    else{
                        q.setDislikes(dislikesRepository.getDislikePerPost(i.getPostID()));
                    }
                    if(i.getCategory()==null){
                        q.setCategory("");
                    }
                    else{
                        q.setCategory(i.getCategory());
                    }
                    if(i.getQuestionCode()==null){
                        q.setCode("");
                    }
                    else{
                        q.setCode(i.getQuestionCode());
                    }

                    List<Comment> comment = commentRepository.getPostbyID(i.getPostID());
                    List<Answer2> answers = new ArrayList<>();
                    for (Comment c : comment) {
                        Answer2 answer = new Answer2();

                        if(c.getCommentCode()==null){
                            answer.setCode("");
                        }
                        else{
                            answer.setCode(c.getCommentCode());
                        }

                        UserNewAdd user1 = new UserNewAdd();
                        if(commentRepository.getCommUserID(c.getCommentID())==null){
                            user1.setUid("");
                        }else{
                            user1.setUid(commentRepository.getCommUserID(c.getCommentID()));
                        }
                        if(userRepository.getUserURL(c.getUserID())==null){
                            user1.setPhotoURL("");
                        }
                        else{
                            user1.setPhotoURL(userRepository.getUserURL(c.getUserID()));
                        }
                        if(userRepository.getUserDisplayName(c.getUserID())==null){
                            user1.setDisplayName("");
                        }
                        else{
                            user1.setDisplayName(userRepository.getUserDisplayName(c.getUserID()));

                        }
                        answer.setUser(user1);
                        if(c.getCommentTitle()==null){
                            answer.setAnswerTitle("");
                        }
                        else{
                            answer.setAnswerTitle(c.getCommentTitle());
                        }
                        if(c.getContent()==null){
                            answer.setAnswerDetails("");
                        }
                        else{
                            answer.setAnswerDetails(c.getContent());
                        }

                        answers.add(answer);
                    }
                    q.setAnswers(answers);
                    result.add(q);

                }
                contor++;
                if (contor > end)
                    break;
            }
            if(result.isEmpty()){
                throw  new NoSuchElementException("Nu-i element") ;
            }
            List<Object> l=new ArrayList<>();
            l.addAll(result);
            JsonFormat x=new JsonFormat(l);
            return x;

        } catch (Exception e) {
            return new ResponseEntity<>(new Eroare(),HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/feed/{userID}/{start}/{end}")
    @Transactional
    public Object getFeed(@PathVariable("userID") String userID,@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        List<String> interest=new ArrayList<>();
        interest=interestRepository.getInterestOfUser(userID);
        List<Post> post=new ArrayList<>();
        List<Post> p=new ArrayList<>();
        for(String i : interest){
            //
            p=postRepository.getPostariByCategory(i);
            post.addAll(p);
        }
        Comparator<Post> compare= new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                Integer x;
                Integer y;
                Integer z;
                Integer z1;
                x= likesRepository.getLikePerPost(p1.getPostID());
                y=dislikesRepository.getDislikePerPost(p1.getPostID());
                z=x-y;
                x= likesRepository.getLikePerPost(p2.getPostID());
                y=dislikesRepository.getDislikePerPost(p2.getPostID());
                z1=x-y;
                return z1.compareTo(z);
            }
        };
        Collections.sort(post,compare);
        Integer contor=start;
        List<QuestionPremium> result=new ArrayList<>();
        for (Post i : post) {
            if (contor >= start && contor <= end) {
                QuestionPremium q = new QuestionPremium();
                q.setQuestionID(i.getPostID());
                UserNewAdd u = new UserNewAdd();
                u.setUid(i.getUserID());
                if(userRepository.getUserDisplayName(i.getUserID())==null){
                    u.setDisplayName("");
                }
                else{
                    u.setDisplayName(userRepository.getUserDisplayName(i.getUserID()));
                }
                if(userRepository.getUserURL(i.getUserID())==null){
                    u.setPhotoURL("");
                }
                else{
                    u.setPhotoURL(userRepository.getUserURL(i.getUserID()));
                }

                q.setUser(u);
                List<String> label = labelRepository.getPostbyID(i.getPostID());
                q.setQuestionLabels(label);
                if(i.getQuestionContent()==null){
                    q.setQuestionContent("");
                }
                else{
                    q.setQuestionContent(i.getQuestionContent());
                }
                if(i.getQuestionTitle()==null){
                    q.setQuestionTitle("");
                }
                else{
                    q.setQuestionTitle(i.getQuestionTitle());
                }

                if(likesRepository.getLikePerPost(i.getPostID())==null){
                    q.setLikes(0);
                }
                else{
                    q.setLikes(likesRepository.getLikePerPost(i.getPostID()));
                }
                if(dislikesRepository.getDislikePerPost(i.getPostID())==null){
                    q.setDislikes(0);

                }
                else{
                    q.setDislikes(dislikesRepository.getDislikePerPost(i.getPostID()));
                }
                if(i.getCategory()==null){
                    q.setCategory("");
                }
                else{
                    q.setCategory(i.getCategory());
                }
                if(i.getQuestionCode()==null){
                    q.setCode("");
                }
                else{
                    q.setCode(i.getQuestionCode());
                }

                List<Comment> comment = commentRepository.getPostbyID(i.getPostID());
                List<Answer2> answers = new ArrayList<>();
                for (Comment c : comment) {
                    Answer2 answer = new Answer2();

                    if(c.getCommentCode()==null){
                        answer.setCode("");
                    }
                    else{
                        answer.setCode(c.getCommentCode());
                    }

                    UserNewAdd user1 = new UserNewAdd();
                    if(commentRepository.getCommUserID(c.getCommentID())==null){
                        user1.setUid("");
                    }else{
                        user1.setUid(commentRepository.getCommUserID(c.getCommentID()));
                    }
                    if(userRepository.getUserURL(c.getUserID())==null){
                        user1.setPhotoURL("");
                    }
                    else{
                        user1.setPhotoURL(userRepository.getUserURL(c.getUserID()));
                    }
                    if(userRepository.getUserDisplayName(c.getUserID())==null){
                        user1.setDisplayName("");
                    }
                    else{
                        user1.setDisplayName(userRepository.getUserDisplayName(c.getUserID()));

                    }
                    answer.setUser(user1);
                    if(c.getCommentTitle()==null){
                        answer.setAnswerTitle("");
                    }
                    else{
                        answer.setAnswerTitle(c.getCommentTitle());
                    }
                    if(c.getContent()==null){
                        answer.setAnswerDetails("");
                    }
                    else{
                        answer.setAnswerDetails(c.getContent());
                    }

                    answers.add(answer);
                }
                q.setAnswers(answers);
                result.add(q);

            }
            contor++;
            if (contor > end)
                break;
        }
        List<Object> l=new ArrayList<>();
        l.addAll(result);
        JsonFormat x=new JsonFormat(l);
        return x;


    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/questions")
    @Transactional
    public Object createQuestion(@RequestBody QuestionPremium newPost){
        Integer nr;
        if(postRepository.getMaxPost()==null){
            nr=0;
        }
        else{
            nr=postRepository.getMaxPost()+1;
        }
        if(postRepository.getUserQuestions(newPost.getUser().getUid())==0) {
            Integer nrul=0;
            if(badgeRepository.getMaxBadge()==null){
                nrul=0;
            }
            else{
                nrul=badgeRepository.getMaxBadge();
            }
            badgeRepository.addBadge(nrul+1,"First question asked!",newPost.getUser().getUid(),"bronze");
        }
        //System.out.println("***"+postRepository.getUserQuestions(newPost.getUser().getUid()));
        postRepository.saveQuestion(nr,newPost.getUser().getUid(),newPost.getQuestionTitle(),newPost.getQuestionContent(),newPost.getLikes(),newPost.getDislikes(),newPost.getDate(),newPost.getCategory(),newPost.getCode());
        for(String i: newPost.getQuestionLabels()){
            labelRepository.saveLabel(i, nr);
        }

         return new ResponseEntity<>(new ReturnMessage("HTTP status will be CREATED"),HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/questions/{id}")
    @Transactional
    public Object deleteQuestion(@PathVariable ("id") Integer id){
        labelRepository.deleteQuestion(id);
        postRepository.deleteQuestion(id);

        return new ResponseEntity<>(new ReturnMessage("HTTP status will be OK"),HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/questions/{id}")
    @Transactional
    public Object showQuestion(@PathVariable ("id") Integer id){
        try {
            Post p = postRepository.getID(id);
            Post i = p;
            QuestionPremiumCuID q = new QuestionPremiumCuID();
            q.setQuestionID(i.getPostID());
            UserNewAdd u = new UserNewAdd();
            u.setUid(i.getUserID());
            if(userRepository.getUserDisplayName(i.getUserID())==null){
                u.setDisplayName("");
            }
            else{
                u.setDisplayName(userRepository.getUserDisplayName(i.getUserID()));
            }
            if(userRepository.getUserURL(i.getUserID())==null){
                u.setPhotoURL("");
            }
            else{
                u.setPhotoURL(userRepository.getUserURL(i.getUserID()));
            }

            q.setUser(u);
            List<String> label = labelRepository.getPostbyID(i.getPostID());
            q.setQuestionLabels(label);
            if(i.getQuestionContent()==null){
                q.setQuestionContent("");
            }
            else{
                q.setQuestionContent(i.getQuestionContent());
            }
            if(i.getQuestionTitle()==null){
                q.setQuestionTitle("");
            }
            else{
                q.setQuestionTitle(i.getQuestionTitle());
            }
            if(likesRepository.getLikePerPost(i.getPostID())==null){
                q.setLikes(0);
            }
            else{
                q.setLikes(likesRepository.getLikePerPost(i.getPostID()));
            }
            if(dislikesRepository.getDislikePerPost(i.getPostID())==null){
                q.setDislikes(0);

            }
            else{
                q.setDislikes(dislikesRepository.getDislikePerPost(i.getPostID()));
            }
            if(i.getCategory()==null){
                q.setCategory("");
            }
            else{
                q.setCategory(i.getCategory());
            }
            if(i.getQuestionCode()==null){
                q.setCode("");
            }
            else{
                q.setCode(i.getQuestionCode());
            }

            List<Comment> comment = commentRepository.getPostbyID(i.getPostID());
            List<AnswerCuID> answers = new ArrayList<>();
            for (Comment c : comment) {
                AnswerCuID answer = new AnswerCuID();
                if(c.getCommentID()==null){
                    answer.setAnswerID(0);
                }else {
                    answer.setAnswerID(c.getCommentID());
                }
                if(c.getCommentCode()==null){
                    answer.setCode("");
                }
                else{
                    answer.setCode(c.getCommentCode());
                }

                UserNewAdd user1 = new UserNewAdd();
                if(commentRepository.getCommUserID(c.getCommentID())==null){
                    user1.setUid("");
                }else{
                    user1.setUid(commentRepository.getCommUserID(c.getCommentID()));
                }
                if(userRepository.getUserURL(c.getUserID())==null){
                    user1.setPhotoURL("");
                }
                else{
                    user1.setPhotoURL(userRepository.getUserURL(c.getUserID()));
                }
                if(userRepository.getUserDisplayName(c.getUserID())==null){
                    user1.setDisplayName("");
                }
                else{
                    user1.setDisplayName(userRepository.getUserDisplayName(c.getUserID()));

                }
                answer.setUser(user1);
                if(c.getCommentTitle()==null){
                    answer.setAnswerTitle("");
                }
                else{
                    answer.setAnswerTitle(c.getCommentTitle());
                }
                if(c.getContent()==null){
                    answer.setAnswerDetails("");
                }
                else{
                    answer.setAnswerDetails(c.getContent());
                }

                answers.add(answer);
            }
            q.setAnswers(answers);
            if(q==null){
                throw new NoSuchElementException("Nu i element");
            }
            return q;
        }
        catch(Exception e){
            return new ResponseEntity<>(new Eroare(),HttpStatus.NOT_FOUND);

        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/categories")
    @Transactional
    public Object getCategories(){
        try {
            List<String> result = new ArrayList<>();
            result = postRepository.getCategory();
            if (result.isEmpty()) {
                throw new NoSuchElementException("Nu-i element");
            }
            List<Object> l = new ArrayList<>();
            l.addAll(result);
            JsonFormat d = new JsonFormat(l);
            return d;
        }
        catch(Exception e){
            return new ResponseEntity<>(new Eroare(),HttpStatus.NOT_FOUND);
        }

    }


}
