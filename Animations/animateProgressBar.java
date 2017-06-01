 private void animateProgressBar(ProgressBar progressBar, int totalValue) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, totalValue);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
       /* animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //do something when the countdown is complete
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });*/
    }