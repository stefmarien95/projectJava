import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MijnSongsDetailComponent } from './mijn-songs-detail.component';

describe('MijnSongsDetailComponent', () => {
  let component: MijnSongsDetailComponent;
  let fixture: ComponentFixture<MijnSongsDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MijnSongsDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MijnSongsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
